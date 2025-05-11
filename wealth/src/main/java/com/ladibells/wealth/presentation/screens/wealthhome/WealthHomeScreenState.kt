package com.ladibells.wealth.presentation.screens.wealthhome

import com.ladibells.datasource.local.entity.Coin

data class WealthHomeScreenState(
    val isLoading: Boolean = false,
    val coins: List<Coin> = emptyList(),
    val error: String = ""
)
