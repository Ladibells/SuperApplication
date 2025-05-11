package com.ladibells.festival.domain.repository

import com.ladibells.datasource.local.entity.FestivalsData
import com.ladibells.utilities.Resource
import kotlinx.coroutines.flow.Flow

interface IFestivalRepository {
    suspend fun getFestivalsData(): Flow<Resource<FestivalsData?>>
}