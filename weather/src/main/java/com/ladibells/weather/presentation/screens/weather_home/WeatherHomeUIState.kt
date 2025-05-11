package com.ladibells.weather.presentation.screens.weather_home

data class WeatherHomeUIState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val locationName: String? = null,
    val temperature: Int = 0,
    val unit: String? = null,
    val weatherIcon: String? = null,
    val observationTime: String? = null,
    val weatherDescription: String? = null,
    val airQuality03: String? = null,
    val sunrise: String? = null,
    val sunset: String? = null
)
