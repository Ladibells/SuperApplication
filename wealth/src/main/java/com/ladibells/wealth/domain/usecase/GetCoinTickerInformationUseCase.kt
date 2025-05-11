package com.ladibells.wealth.domain.usecase

import com.ladibells.utilities.Resource
import com.ladibells.wealth.domain.model.CoinTickerInformation
import com.ladibells.wealth.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCoinTickerInformationUseCase @Inject constructor(
    private val repository: CoinRepository
) {
    suspend operator fun invoke(coinId: String) : Flow<Resource<CoinTickerInformation>> {
        return repository.getCoinTickerInformation(coinId)
    }

}