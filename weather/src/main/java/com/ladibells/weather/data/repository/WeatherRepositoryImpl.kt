package com.ladibells.weather.data.repository

import com.ladibells.utilities.Resource
import com.ladibells.utilities.logging.AppLogger
import com.ladibells.weather.data.remote.WeatherApi
import com.ladibells.weather.data.remote.dto.toCurrentWeatherResponse
import com.ladibells.weather.domain.model.CurrentWeatherResponse
import com.ladibells.weather.domain.repository.IWeatherRepository
import kotlinx.coroutines.flow.Flow
import retrofit2.HttpException
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import java.io.IOException

class WeatherRepositoryImpl @Inject constructor(
    private val api: WeatherApi
) : IWeatherRepository {
    override suspend fun getCurrentWeather(
        accessKey: String,
        query: String
    ): Flow<Resource<CurrentWeatherResponse>> = flow {
        try {
            emit(Resource.Loading())
            val currentWeatherResponse = api.getCurrentWeather(accessKey, query).toCurrentWeatherResponse()
            emit(Resource.Success(currentWeatherResponse))
            AppLogger.d(message = "Inside success of getCurrentWeather")
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
            AppLogger.d(message = "Inside error of HttpException ${e.localizedMessage}")

        } catch (e: IOException) {
            AppLogger.d(message = "Inside error of IOException ${e.localizedMessage}")
            emit(Resource.Error("Couldn't reach server. Check your internet connection."))
        }
    }
}