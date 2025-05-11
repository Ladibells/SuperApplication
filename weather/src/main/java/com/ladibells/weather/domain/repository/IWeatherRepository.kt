package com.ladibells.weather.domain.repository

import com.ladibells.utilities.Resource
import com.ladibells.weather.domain.model.CurrentWeatherResponse
import kotlinx.coroutines.flow.Flow

interface IWeatherRepository {
    suspend fun getCurrentWeather(
        accessKey: String,
        query: String
    ) : Flow<Resource<CurrentWeatherResponse>>
}