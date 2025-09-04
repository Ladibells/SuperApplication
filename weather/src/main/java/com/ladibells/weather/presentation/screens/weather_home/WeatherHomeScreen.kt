package com.ladibells.weather.presentation.screens.weather_home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil3.compose.AsyncImage
import com.ladibells.design.components.AppToolBar
import com.ladibells.design.components.TextComponent
import com.ladibells.weather.R
import com.ladibells.weather.presentation.components.WeatherFieldDetailsComponents

@Composable
fun WeatherHomeScreen(
    wealthHomeViewModel: WeatherHomeViewModel = hiltViewModel(),
    primaryButtonClicked: () -> Unit = {},
) {

    val state = wealthHomeViewModel.state.value

    Scaffold(
        topBar = {
            AppToolBar(
                title = if (state.locationName.isNullOrEmpty()) state.locationName else stringResource(R.string.weather_details) ,
                isBackButtonVisible = true,
                primaryButtonClicked = { primaryButtonClicked.invoke() }
            )
        }
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {

            state.temperature?.also {
                TextComponent(
                    modifier = Modifier
                        .wrapContentWidth()
                        .wrapContentHeight()
                        .padding(all = 18.dp)
                        .align(Alignment.CenterHorizontally),
                    textValue = it,
                    fontSizeValue = 34.sp
                )
            }

            state.weatherIcon?.also {
                AsyncImage(
                    model = it,
                    contentDescription = stringResource(R.string.weather_current_status_image),
                    modifier = Modifier
                        .size(100.dp)
                        .align(Alignment.CenterHorizontally),
                    contentScale = ContentScale.Fit
                )
            }

            state.weatherIcon?.also {
                AsyncImage(
                    modifier = Modifier
                        .size(100.dp)
                        .align(Alignment.CenterHorizontally),
                    model = it,
                    contentDescription = stringResource(R.string.weather_current_status_image),
                    contentScale = ContentScale.Fit
                )
            }

            state.summaryOfTheDay?.also {
                TextComponent(
                    modifier = Modifier
                        .wrapContentSize()
                        .padding(all = 18.dp) // or it could come after calling align
                        .align(Alignment.CenterHorizontally),
                    textValue = it,
                    fontSizeValue = 20.sp
                )
            }

            state.summaryIconOfTheDay?.also {
                AsyncImage(
                    modifier = Modifier
                        .size(100.dp)
                        .align(Alignment.CenterHorizontally),
                    model = it,
                    contentDescription = null,
                    contentScale = ContentScale.Fit
                )

            }

            state.airQualityO3?.also {
                WeatherFieldDetailsComponents(
                    title = stringResource(R.string.air_details),
                    value = it,
                    icon = R.drawable.ic_air1
                )
            }

            state.avgHumidity?.also {
                WeatherFieldDetailsComponents(
                    title = stringResource(R.string.avg_humidity),
                    value = it.toString(),
                    icon = null
                )
            }
        }
    }
}

@Preview
@Composable
fun WeatherHomeScreenPreview() {
    WeatherHomeScreen()
}