package com.ladibells.festival.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.ladibells.design.components.AppToolBar
import com.ladibells.design.components.TextComponent
import com.ladibells.design.ui.theme.holiColor1
import com.ladibells.design.ui.theme.holiColor4
import com.ladibells.design.ui.theme.primaryColor
import com.ladibells.design.ui.theme.whiteColor
import com.ladibells.festival.R
import com.ladibells.festival.presentation.components.FestivalLottieAnimationComponent
import com.ladibells.festival.presentation.components.FestivalMessagesComponent
import com.ladibells.utilities.SuperUtilities

@Composable
fun FestivalHomeScreen(
    primaryButtonClicked: () -> Unit = {},
    viewModel: FestivalViewModel = hiltViewModel()
) {

    val state = viewModel.state.value
    Scaffold(
        topBar = {
            AppToolBar(
                title = state.festivalName ?: "",
                isBackButtonVisible = true,
                primaryButtonClicked = {
                    primaryButtonClicked()
                },
                backgroundColor = Color.Transparent
            )
        }
    ) { innerPadding ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(holiColor4, whiteColor, holiColor1)
                    )
                )
                .padding(innerPadding)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                FestivalLottieAnimationComponent(
                    rawResource = R.raw.holi
                )

                TextComponent(
                    modifier = Modifier
                        .wrapContentSize()
                        .padding(all = 12.dp),
                    textValue = "\"${state.festivalDescription}\"",
                    fontSizeValue = 24.sp,
                    fontWeightValue = FontWeight.Normal,
                    textColorValue = primaryColor
                )

                state.festivalDate?.also {
                    TextComponent(
                        modifier = Modifier
                            .wrapContentSize()
                            .align(Alignment.CenterHorizontally)
                            .padding(all = 12.dp),
                        textValue = SuperUtilities.toDate(it),
                        fontSizeValue = 24.sp,
                        fontWeightValue = FontWeight.Normal,
                        textColorValue = primaryColor
                    )
                }

                FestivalMessagesComponent()
            }
        }
    }
}