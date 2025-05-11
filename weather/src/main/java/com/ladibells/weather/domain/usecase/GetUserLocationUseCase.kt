package com.ladibells.weather.domain.usecase

import com.ladibells.datasource.local.LocalDataSource
import com.ladibells.utilities.logging.AppLogger
import javax.inject.Inject

class GetUserLocationUseCase @Inject constructor(
    private val localDataSource: LocalDataSource
) {
    operator fun invoke() : String {
        AppLogger.d(message = "Inside GetUserLocationUseCae invoke function. Fetched Location is ${localDataSource.getUserLocationFromDB()}")
        return localDataSource.getUserLocationFromDB()
    }
}