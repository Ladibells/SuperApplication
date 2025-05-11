package com.ladibells.festival.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.ladibells.festival.R

@Composable
fun FestivalLottieAnimationComponent(rawResource: Int) {
    val composition by rememberLottieComposition(
        spec = LottieCompositionSpec.RawRes(rawResource)
    )

    LottieAnimation(
        composition = composition,
        modifier = Modifier.fillMaxWidth()
            .height(400.dp),
        contentScale = ContentScale.Fit,
        restartOnPlay = true,
        iterations = 10
    )
}