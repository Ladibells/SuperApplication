package com.ladibells.weather.presentation.screens.weather_home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ladibells.utilities.Resource
import com.ladibells.utilities.constants.AppConstants.WEATHER_ACCESS_KEY
import com.ladibells.utilities.logging.AppLogger
import com.ladibells.weather.domain.usecase.GetCurrentWeatherUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherHomeViewModel @Inject constructor(
    private val getCurrentWeatherUseCase: GetCurrentWeatherUseCase
) : ViewModel() {
    private val _state = mutableStateOf(WeatherHomeUIState())
    val state: State<WeatherHomeUIState> = _state

    init {
        AppLogger.d(message = "Inside WeatherHomeViewModel init")
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
                    _state.value = WeatherHomeUIState(
                        isLoading = false,
                        error = result.message ?: "An unexpected error occurred"
                    )
                }
                is Resource.Loading -> {
                    _state.value = WeatherHomeUIState(isLoading = true)
                }
                is Resource.Success -> {
                    _state.value = WeatherHomeUIState(
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
                }
            }
        }.launchIn(viewModelScope)
    }
}