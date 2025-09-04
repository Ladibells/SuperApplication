package com.ladibells.weather.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ladibells.design.components.TextComponent
import com.ladibells.design.ui.theme.blackColor
import com.ladibells.design.ui.theme.lightPrimaryColor
import com.ladibells.design.ui.theme.primaryColor
import com.ladibells.design.ui.theme.whiteColor
import com.ladibells.weather.R

@Composable
fun WeatherFieldDetailsComponents(
    title: String,
    value: String,
    icon: Int? = null
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(all = 12.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(lightPrimaryColor)
    ) {
        Spacer(modifier = Modifier.size(18.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
        ) {

            icon?.also {
                Image(
                    painter = painterResource(icon),
                    contentDescription = null,
                    modifier = Modifier
                        .size(28.dp)
                )
            }
            TextComponent(
                modifier = Modifier
                    .wrapContentWidth()
                    .wrapContentHeight()
                    .padding(horizontal = 8.dp),
                textValue = title,
                fontSizeValue = 18.sp,
                textColorValue = whiteColor,
                fontWeightValue = FontWeight.Medium
            )
        }

        Spacer(modifier = Modifier.size(8.dp))

        TextComponent(
            modifier = Modifier
                .wrapContentWidth()
                .wrapContentHeight()
                .padding(horizontal = 24.dp),
            textValue = value,
            fontSizeValue = 34.sp,
            textColorValue = whiteColor,
            fontWeightValue = FontWeight.Normal
        )
        Spacer(modifier = Modifier.size(18.dp))
    }
}


@Composable
@Preview(showBackground = true, showSystemUi = true)
fun WeatherFieldDetailsComponentsPreview() {
    WeatherFieldDetailsComponents(
        title = "Temperature",
        value = "23°C",
        icon = R.drawable.ic_air1
    )
}