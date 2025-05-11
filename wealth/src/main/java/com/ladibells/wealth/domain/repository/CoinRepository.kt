package com.ladibells.wealth.domain.repository

import com.ladibells.datasource.local.entity.Coin
import com.ladibells.utilities.Resource
import com.ladibells.wealth.domain.model.CoinDetail
import com.ladibells.wealth.domain.model.CoinTickerInformation
import kotlinx.coroutines.flow.Flow

interface CoinRepository {
    suspend fun getCoins(): Flow<Resource<List<Coin>>>
    suspend fun getCoinDetails(coinId: String) : Flow<Resource<CoinDetail>>
    suspend fun getCoinTickerInformation(coinId: String) : Flow<Resource<CoinTickerInformation>>

}