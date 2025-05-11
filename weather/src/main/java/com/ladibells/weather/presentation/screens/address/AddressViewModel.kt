package com.ladibells.weather.presentation.screens.address

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ladibells.utilities.Resource
import com.ladibells.utilities.logging.AppLogger
import com.ladibells.weather.domain.usecase.GetPopularCitiesUseCase
import com.ladibells.weather.domain.usecase.GetUserLocationUseCase
import com.ladibells.weather.domain.usecase.SaveUserLocationUseCae
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddressViewModel @Inject constructor(
    private val getPopularCitiesUseCase: GetPopularCitiesUseCase,
    private val saveUserLocationUseCae: SaveUserLocationUseCae,
    private val getUserLocationUseCase: GetUserLocationUseCase
) : ViewModel() {

    private val _state = mutableStateOf(AddressScreenState())
    val state : State<AddressScreenState> = _state

    init {
        AppLogger.d(message = "Inside AddressViewModel")
        getPopularCities()
        getUserLastLocationFromDB()
    }

    private fun getPopularCities() {
        AppLogger.d(message = "Inside getPopularCities")
        viewModelScope.launch {
            AppLogger.d(message = "Inside viewModelScope.launch")
            getPopularCitiesUseCase().onEach { result ->
                when (result) {
                    is Resource.Loading -> {
                        _state.value = AddressScreenState(isLoading = true)
                        AppLogger.d(message = "Inside Resource.Loading")
                    }
                    is Resource.Success -> {
                        _state.value = AddressScreenState(
                            popularCities = result.data ?: emptyList(),
                            isLoading = false
                        )
                        AppLogger.d(message = "Inside Success ${result.data?.size}")
                    }
                    is Resource.Error -> {
                        _state.value = AddressScreenState(
                            errorMessage = result.message ?: "Something went wrong",
                            isLoading = false
                        )
                        AppLogger.d(message = "Inside Error ${result.message}")
                    }
                }
            }.launchIn(viewModelScope)
        }
    }

    fun onEvent(event: LocationUIEvent) {
        when (event) {
            is LocationUIEvent.CityItemClicked -> {
                AppLogger.d(message = "Inside CityItemClicked, city is ${event.cityName}")
                saveUserLocationUseCae.invoke(event.cityName)
                updateCitiesState(cityName = event.cityName)
                getUserLastLocationFromDB()
            }
        }
    }

    private fun updateCitiesState(cityName: String) {
        val updatedCities = _state.value.popularCities.map { city ->
            if (city.name == cityName) {
                city.copy(isSelected = true)
            } else {
                city.copy(isSelected = false)
            }
        }
        _state.value = _state.value.copy(
            isLoading = false,
            popularCities = updatedCities,
            selectedCity = cityName
        )
    }

    private fun getUserLastLocationFromDB() {
        AppLogger.d(message = "Inside getUserLastLocationFromDB")
        val cityName = getUserLocationUseCase.invoke()
        if (cityName.isNotEmpty()) {
            AppLogger.d(message = "Inside getUserLastLocationFromDB, cityName is $cityName")
            _state.value = _state.value.copy(selectedCity = cityName)
            updateCitiesState(cityName)
        }
    }

}

