package com.ladibells.weather.presentation.screens.weather_home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ladibells.utilities.Resource
import com.ladibells.utilities.constants.AppConstants.WEATHER_ACCESS_KEY
import com.ladibells.utilities.logging.AppLogger
import com.ladibells.weather.domain.usecase.GetCurrentWeatherUseCase
import com.ladibells.weather.domain.usecase.GetMarineWeatherForecastUseCase
import com.ladibells.weather.domain.usecase.GetUserLocationUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.math.ceil

@HiltViewModel
class WeatherHomeViewModel @Inject constructor(
    private val getCurrentWeatherUseCase: GetCurrentWeatherUseCase,
    private val getUserLocationUseCase: GetUserLocationUseCase,
    private val getMarineWeatherForecastUseCase: GetMarineWeatherForecastUseCase
) : ViewModel() {
    private val _state = mutableStateOf(WeatherHomeUIState())
    val state: State<WeatherHomeUIState> = _state

    init {
        AppLogger.d(message = "Inside WeatherHomeViewModel init")
        getUserLocationFromDB()
    }

    private fun fetchCurrentLocationWeather(location: String) {
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
                    _state.value = _state.value.copy(isLoading = true)
                }
                is Resource.Success -> {
                    _state.value = _state.value.copy(
                        locationName = result.data?.locationName,
                        temperature = "${ceil(result.data?.temperatureC ?: 0.0).toInt().toString()}°C",
                        weatherIcon = result.data?.weatherIcon,
                        observationTime = result.data?.observationTime,
                        airQualityO3 = ceil(result.data?.airQuality03 ?: 0.0).toInt().toString(),
                        isLoading = false,
                        error = null
                    )
                    AppLogger.d(message = "Inside WeatherHomeViewModel getCurrentWeatherUseCase Success")
                    AppLogger.d(message = "Fetched WeatherHomeUIState = ${_state.value}")
                    getMarineWeatherForecast(accessKey, query)
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun getUserLocationFromDB() {
        AppLogger.d(message = "Inside getUserLocationFromDB in WeatherHomeViewModel")
        val cityName = getUserLocationUseCase.invoke()
        if (cityName.isNotEmpty()) {
            AppLogger.d(message = "Inside getUserLocationFromDB, Fetched User last location from DB. cityName is $cityName")
            _state.value = _state.value.copy(locationName = cityName)
            fetchCurrentLocationWeather(location = cityName)
        }

    }

    private suspend fun getMarineWeatherForecast(accessKey: String, query: String) {
        getMarineWeatherForecastUseCase(accessKey, query).onEach { result ->
            when (result) {
                is Resource.Error -> {
                    _state.value = WeatherHomeUIState(
                        isLoading = false,
                        error = result.message ?: "An unexpected error occurred"
                    )
                }
                is Resource.Loading -> {
                    _state.value = _state.value.copy(isLoading = true)
                }
                is Resource.Success -> {
                    _state.value = _state.value.copy(
                        locationName = result.data?.locationName,
                        forecastDayDate = result.data?.forecastDayDate,
                        maxTempC = result.data?.maxTempC,
                        minTempC = result.data?.minTempC,
                        avgHumidity = ceil(result.data?.avgHumidity ?: 0.0).toInt().toString(),
                        summaryOfTheDay = result.data?.summaryOfTheDay,
                        summaryIconOfTheDay = result.data?.summaryIconOfTheDay,
                        sunrise = result.data?.sunrise,
                        sunset = result.data?.sunset,
                        moonrise = result.data?.moonrise,
                        moonset = result.data?.moonset,
                    )
                    AppLogger.d(message = "Inside WeatherHomeViewModel getMarineWeatherForecastUseCase Success")
                    AppLogger.d(message = "Fetched WeatherHomeUIState = ${_state.value}")
                }
            }
        }
            .launchIn(viewModelScope)
    }
}