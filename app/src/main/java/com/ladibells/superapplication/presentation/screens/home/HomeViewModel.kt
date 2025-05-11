package com.ladibells.superapplication.presentation.screens.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ladibells.festival.domain.usecase.GetFestivalDataUseCase
import com.ladibells.utilities.Resource
import com.ladibells.utilities.constants.AppConstants.WEATHER_ACCESS_KEY
import com.ladibells.utilities.logging.AppLogger
import com.ladibells.weather.domain.usecase.GetCurrentWeatherUseCase
import com.ladibells.weather.domain.usecase.GetUserLocationUseCase
import com.ladibells.weather.presentation.screens.weather_home.WeatherHomeUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getFestivalDataUseCase: GetFestivalDataUseCase,
    private val getUserLocationUseCase: GetUserLocationUseCase,
    private val getCurrentWeatherUseCase: GetCurrentWeatherUseCase
) : ViewModel() {

    private val _state = mutableStateOf(HomeUIState())
    val state: State<HomeUIState> = _state

    init {
        AppLogger.d(message = "Inside Home ViewModel we have supabase")
//        getFestivalsData()
        getUserLastLocationFromDB()
        refresh()
    }

    private fun getFestivalsData() {
        viewModelScope.launch {
            getFestivalDataUseCase().onEach { result ->
                when (result) {
                    is Resource.Error -> {
                        _state.value = HomeUIState(
                            error = result.message ?: "An unexpected error occurred",
                            isLoading = false
                        )
                    }
                    is Resource.Loading -> {
                        _state.value = HomeUIState(isLoading = true)
                    }
                    is Resource.Success -> {
                        if (result.data != null) {
                            _state.value = HomeUIState(
                                isLoading = false,
                                festivalName = result.data?.festivalName ?: "",
                                festivalDate = result.data?.festivalDate ?: "",
                                festivalDescription = result.data?.festivalDescription ?: ""
                            )
                        } else {
                            _state.value = HomeUIState(isLoading = false,)
                        }
                    }
                }
            }.launchIn(viewModelScope)
        }
    }

    private fun getUserLastLocationFromDB() {
        AppLogger.d(message = "Inside getUserLastLocationFromDB")
        val cityName = getUserLocationUseCase.invoke()
        if (cityName.isNotEmpty()) {
            AppLogger.d(message = "Inside getUserLastLocationFromDB, cityName is $cityName")
            _state.value = _state.value.copy(selectedCity = cityName)
            fetchCurrentLocationWeather(location = cityName)
        }
    }

    fun refresh() {
        getUserLastLocationFromDB()
    }

    fun fetchCurrentLocationWeather(location: String) {
        AppLogger.d(message = "Inside fetchCurrentLocationWeather")
        viewModelScope.launch {
            AppLogger.d(message = "Inside WeatherHomeViewModel fetchCurrentLocationWeather")
            getCurrentWeather(accessKey = WEATHER_ACCESS_KEY, query = location)
        }

    }

    private suspend fun getCurrentWeather(accessKey: String, query: String) {
        getCurrentWeatherUseCase(accessKey, query).onEach { result ->
            when (result) {
                is Resource.Error -> {
                    _state.value = HomeUIState(
                        isLoading = false,
                        error = result.message ?: "An unexpected error occurred"
                    )
                }
                is Resource.Loading -> {
                    _state.value = HomeUIState(isLoading = true)
                }
                is Resource.Success -> {
                    _state.value = HomeUIState(
                        weatherHomeUIState = WeatherHomeUIState(
                            locationName = result.data?.locationName,
                            temperature = result.data?.temperature ?: 0,
                            unit = result.data?.unit,
                            weatherIcon = result.data?.weatherIcon,
                            observationTime = result.data?.observationTime,
                            weatherDescription = result.data?.weatherDescription,
                            airQuality03 = result.data?.airQuality03,
                            sunrise = result.data?.sunrise,
                            sunset = result.data?.sunset,
                            isLoading = false,
                            error = null
                        )
                    )
                }
            }
        }.launchIn(viewModelScope)
    }
}