package com.ladibells.weather.domain.usecase

import com.ladibells.utilities.Resource
import com.ladibells.weather.domain.model.CurrentWeatherResponse
import com.ladibells.weather.domain.repository.IWeatherRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCurrentWeatherUseCase @Inject constructor(
    private val repository: IWeatherRepository
) {
    suspend operator fun invoke(accessKey: String, query: String) : Flow<Resource<CurrentWeatherResponse>> {
        return repository.getCurrentWeather(accessKey, query)
    }
}