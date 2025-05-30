package com.ladibells.weather.presentation.screens.weather_home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.ladibells.design.components.AppToolBar

@Composable
fun WeatherHomeScreen() {
    Scaffold(
        topBar = {
            AppToolBar(
                title = "Weather",
                isBackButtonVisible = true
            )
        }
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {  }
    }
}

@Preview
@Composable
fun WeatherHomeScreenPreview() {
    WeatherHomeScreen()
}