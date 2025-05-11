package com.ladibells.weather.domain.usecase

import com.ladibells.datasource.local.entity.CityItem
import com.ladibells.utilities.Resource
import com.ladibells.weather.domain.repository.IAddressRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPopularCitiesUseCase @Inject constructor(
    private val addressRepository: IAddressRepository

) {
    suspend operator fun invoke() : Flow<Resource<List<CityItem>>> {
        return addressRepository.getPopularCities()
    }
}