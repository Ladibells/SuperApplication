package com.ladibells.weather.presentation.screens.address

sealed class LocationUIEvent {
    data class CityItemClicked(val cityName: String) : LocationUIEvent()
}