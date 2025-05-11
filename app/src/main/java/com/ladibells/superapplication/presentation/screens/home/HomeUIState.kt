package com.ladibells.superapplication.presentation.screens.home

import com.ladibells.weather.presentation.screens.weather_home.WeatherHomeUIState

data class HomeUIState (
    val isLoading: Boolean = false,
    val error: String? = null,
    val festivalName: String ?= null,
    val festivalDate: String ?= null,
    val festivalDescription: String ?= null,
    val selectedCity: String = "",
    val weatherHomeUIState: WeatherHomeUIState? = null

)