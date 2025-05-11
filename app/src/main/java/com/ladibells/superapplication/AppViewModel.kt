package com.ladibells.superapplication

import androidx.lifecycle.ViewModel
import com.ladibells.wealth.domain.usecase.GetCoinsUseCase
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class AppViewModel @Inject constructor(
    private val getCoinsUseCase: GetCoinsUseCase
) : ViewModel() {

    suspend fun getCoins() {
//        getCoinsUseCase().
    }
}