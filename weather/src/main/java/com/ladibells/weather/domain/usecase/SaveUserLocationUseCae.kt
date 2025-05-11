package com.ladibells.weather.domain.usecase

import com.ladibells.datasource.local.LocalDataSource
import com.ladibells.utilities.logging.AppLogger
import javax.inject.Inject

class SaveUserLocationUseCae @Inject constructor(
    private val localDataSource: LocalDataSource
) {
    operator fun invoke(cityName: String) {
        localDataSource.insertUserLocationInDB(cityName)
        AppLogger.d(message = "Inside SaveUserLocationUseCae invoke function")
    }
}