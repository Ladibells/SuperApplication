package com.ladibells.weather.domain.model

data class CurrentWeatherResponse(
    val locationName: String,
    val temperature: Int,
    val unit: String,
    val weatherIcon: String?,
    val observationTime: String,
    val weatherDescription: String?,
    val airQuality03: String,
    val sunrise: String,
    val sunset: String
)
