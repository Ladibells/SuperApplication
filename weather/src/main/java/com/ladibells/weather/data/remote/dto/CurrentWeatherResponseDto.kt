package com.ladibells.weather.data.remote.dto

import com.google.gson.annotations.SerializedName
import com.ladibells.weather.domain.model.CurrentWeatherResponse
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


//@Serializable
//data class CurrentWeatherResponseDto(
//    val location: Location,
//    val current: Current
//)
//
//@Serializable
//data class WeatherData(
//    val location: Location,
//    val current: Current
//)
//
//@Serializable
//data class Location(
//    val name: String,
//    val region: String,
//    val country: String,
//    val lat: Double,
//    val lon: Double,
//    @SerialName("tz_id") val tzId: String,
//    @SerialName("localtime_epoch") val localtimeEpoch: Long,
//    val localtime: String
//)
//
//@Serializable
//data class Current(
//    @SerialName("last_updated_epoch") val lastUpdatedEpoch: Long,
//    @SerialName("last_updated") val lastUpdated: String,
//    @SerialName("temp_c") val tempC: Double, // Changed to Double as it can be decimal
//    @SerialName("temp_f") val tempF: Double,
//    @SerialName("is_day") val isDay: Int, // 1 for day, 0 for night
//    val condition: Condition,
//    @SerialName("wind_mph") val windMph: Double,
//    @SerialName("wind_kph") val windKph: Double,
//    @SerialName("wind_degree") val windDegree: Int,
//    @SerialName("wind_dir") val windDir: String,
//    @SerialName("pressure_mb") val pressureMb: Double, // Changed to Double
//    @SerialName("pressure_in") val pressureIn: Double,
//    @SerialName("precip_mm") val precipMm: Double,
//    @SerialName("precip_in") val precipIn: Double, // Changed to Double
//    val humidity: Int,
//    val cloud: Int,
//    @SerialName("feelslike_c") val feelslikeC: Double,
//    @SerialName("feelslike_f") val feelslikeF: Double,
//    @SerialName("vis_km") val visKm: Double, // Changed to Double
//    @SerialName("vis_miles") val visMiles: Double, // Changed to Double
//    val uv: Double, // Changed to Double
//    @SerialName("gust_mph") val gustMph: Double,
//    @SerialName("gust_kph") val gustKph: Double,
//    @SerialName("air_quality") val airQuality: AirQuality // Made nullable as it might not always be present
//)
//
//@Serializable
//data class Condition(
//    val text: String,
//    val icon: String,
//    val code: Int
//)
//
//@Serializable
//data class AirQuality(
//    val co: Double,
//    @SerialName("no2") val no2: Double,
//    @SerialName("o3") val o3: Double,
//    @SerialName("so2") val so2: Double,
//    @SerialName("pm2_5") val pm25: Double, // Corrected name to be more Kotlin-idiomatic
//    val pm10: Double,
//    @SerialName("us-epa-index") val usEpaIndex: Int,
//    @SerialName("gb-defra-index") val gbDefraIndex: Int
//)

// No @Serializable needed for Gson
data class CurrentWeatherResponseDto( // Renamed to avoid conflict if you have kotlinx.serialization classes
    @SerializedName("location") val location: Location,
    @SerializedName("current") val current: Current
)

data class Location(
    @SerializedName("name") val name: String,
    @SerializedName("region") val region: String,
    @SerializedName("country") val country: String,
    @SerializedName("lat") val lat: Double,
    @SerializedName("lon") val lon: Double,
    @SerializedName("tz_id") val tzId: String,
    @SerializedName("localtime_epoch") val localtimeEpoch: Long,
    @SerializedName("localtime") val localtime: String
)

data class Current(
    @SerializedName("last_updated_epoch") val lastUpdatedEpoch: Long,
    @SerializedName("last_updated") val lastUpdated: String,
    @SerializedName("temp_c") val tempC: Double, // Using Double for potential decimal values
    @SerializedName("temp_f") val tempF: Double,
    @SerializedName("is_day") val isDay: Int,
    @SerializedName("condition") val condition: Condition,
    @SerializedName("wind_mph") val windMph: Double,
    @SerializedName("wind_kph") val windKph: Double,
    @SerializedName("wind_degree") val windDegree: Int,
    @SerializedName("wind_dir") val windDir: String,
    @SerializedName("pressure_mb") val pressureMb: Double, // Using Double
    @SerializedName("pressure_in") val pressureIn: Double,
    @SerializedName("precip_mm") val precipMm: Double,
    @SerializedName("precip_in") val precipIn: Double, // Using Double
    @SerializedName("humidity") val humidity: Int,
    @SerializedName("cloud") val cloud: Int,
    @SerializedName("feelslike_c") val feelslikeC: Double,
    @SerializedName("feelslike_f") val feelslikeF: Double,
    @SerializedName("vis_km") val visKm: Double, // Using Double
    @SerializedName("vis_miles") val visMiles: Double, // Using Double
    @SerializedName("uv") val uv: Double, // Using Double
    @SerializedName("gust_mph") val gustMph: Double,
    @SerializedName("gust_kph") val gustKph: Double,
    @SerializedName("air_quality") val airQuality: AirQuality // Nullable if it might be missing
)

data class Condition(
    @SerializedName("text") val text: String,
    @SerializedName("icon") val icon: String,
    @SerializedName("code") val code: Int
)

data class AirQuality(
    @SerializedName("co") val co: Double,
    @SerializedName("no2") val no2: Double,
    @SerializedName("o3") val o3: Double,
    @SerializedName("so2") val so2: Double,
    @SerializedName("pm2_5") val pm25: Double, // Explicitly naming for clarity, though Gson might map pm2_5 to pm25 by default
    @SerializedName("pm10") val pm10: Double,
    @SerializedName("us-epa-index") val usEpaIndex: Int,
    @SerializedName("gb-defra-index") val gbDefraIndex: Int
)


fun CurrentWeatherResponseDto.toCurrentWeatherResponse(): CurrentWeatherResponse {
    return CurrentWeatherResponse(
        locationName = location.name,
        temperatureC = current.tempC,
        weatherIcon = "https:${current.condition.icon}",
        observationTime = current.lastUpdated,
        airQuality03 = current.airQuality.o3,
        feelsLikeC = current.feelslikeC,
        windSpeed = current.windKph
    )
}