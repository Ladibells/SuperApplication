package com.ladibells.wealth.domain.usecase

import com.ladibells.utilities.Resource
import com.ladibells.datasource.local.entity.Coin
import com.ladibells.wealth.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCoinsUseCase @Inject constructor(
    private val repository: CoinRepository
) {
    suspend operator fun invoke() : Flow<Resource<List<Coin>>> {
        return repository.getCoins()
    }

}