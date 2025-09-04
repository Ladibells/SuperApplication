package com.ladibells.weather.domain.usecase

import com.ladibells.utilities.Resource
import com.ladibells.weather.domain.model.MarineWeatherForecast
import com.ladibells.weather.domain.repository.IWeatherRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMarineWeatherForecastUseCase @Inject constructor(
    private val repository: IWeatherRepository
) {
    suspend operator fun invoke(
        accessKey: String,
        query: String
    ): Flow<Resource<MarineWeatherForecast>> {
        return repository.getMarineWeatherForecast(accessKey, query)
    }

}