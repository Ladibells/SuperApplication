package com.ladibells.weather.data.remote

import com.ladibells.weather.data.remote.dto.CurrentWeatherResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET("/current")
    fun getCurrentWeather(
        @Query("access_key") accessKey: String,
        @Query("query") query: String
    ) : CurrentWeatherResponseDto
}

