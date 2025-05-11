package com.ladibells.weather.domain.repository

import com.ladibells.datasource.local.entity.CityItem
import com.ladibells.utilities.Resource
import kotlinx.coroutines.flow.Flow

interface IAddressRepository {
    suspend fun getPopularCities(): Flow<Resource<List<CityItem>>>
}