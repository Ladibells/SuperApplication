package com.ladibells.festival.domain.usecase

import com.ladibells.datasource.local.entity.FestivalsData
import com.ladibells.festival.domain.repository.IFestivalRepository
import com.ladibells.utilities.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetFestivalDataUseCase @Inject constructor(
    private val repository: IFestivalRepository
) {
    suspend operator fun invoke() : Flow<Resource<FestivalsData?>> {
        return repository.getFestivalsData()
    }
}