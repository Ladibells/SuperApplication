package com.ladibells.weather.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import com.google.gson.annotations.SerializedName
import com.ladibells.weather.domain.model.CurrentWeatherResponse


@Serializable
data class CurrentWeatherResponseDto(
    val request: Request,
    val location: Location,
    val current: Current
)

@Serializable
data class Request(
    val type: String,
    val query: String,
    val language: String,
    val unit: String
)

@Serializable
data class Location(
    val name: String,
    val country: String,
    val region: String,
    val lat: String,
    val lon: String,
    @SerialName("timezone_id") val timezoneId: String,
    val localtime: String,
    @SerialName("localtime_epoch") val localtimeEpoch: Long,
    @SerialName("utc_offset") val utcOffset: String
)

@Serializable
data class Current(
    @SerialName("observation_time") val observationTime: String,
    @SerialName("temparature") val temperature: Int, // Corrected typo
    @SerialName("weather_code") val weatherCode: Int,
    @SerialName("weather_icons") val weatherIcons: List<String>,
    @SerialName("weather_descriptions") val weatherDescriptions: List<String>,
    val astro: Astro,
    @SerialName("air_quality") val airQuality: AirQuality,
    @SerialName("wind_speed") val windSpeed: Int,
    @SerialName("wind_degree") val windDegree: Int,
    @SerialName("wind_dir") val windDir: String,
    val pressure: Int,
    val precip: Int,
    val humidity: Int,
    val cloudcover: Int,
    val feelslike: Int,
    @SerialName("uv_index") val uvIndex: Int,
    val visibility: Int
)

@Serializable
data class Astro(
    val sunrise: String,
    val sunset: String,
    val moonrise: String,
    val moonset: String,
    @SerialName("moon_phase") val moonPhase: String,
    @SerialName("moon_illumination") val moonIllumination: Int
)

@Serializable
data class AirQuality(
    val co: String,
    val no2: String,
    val o3: String,
    val so2: String,
    @SerialName("pm2_5") val pm2_5: String,
    val pm10: String,
    @SerialName("us-epa-index") val usEpaIndex: String,
    @SerialName("gb-defra-index") val gbDefraIndex: String
)

fun CurrentWeatherResponseDto.toCurrentWeatherResponse(): CurrentWeatherResponse {
    return CurrentWeatherResponse(
        locationName = location.name,
        temperature = current.temperature,
        unit = request.unit,
        weatherIcon = current.weatherIcons.firstOrNull(),
        observationTime = current.observationTime,
        weatherDescription = current.weatherDescriptions.firstOrNull(),
        airQuality03 = current.airQuality.o3,
        sunrise = current.astro.sunrise,
        sunset = current.astro.sunset
    )
}