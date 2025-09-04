package com.ladibells.design.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.ladibells.design.ui.theme.Purple40
import com.ladibells.design.ui.theme.whiteColor

@Composable
fun BannerComponent(
    title: String? = null,
    description: String? = null,
    imageUrl: String? = null,
    resourceValue: Int? = null,
    bannerClicked: () -> Unit = {}
    ) {
    Box (
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .clickable {
                bannerClicked()
            }
            .padding(12.dp)
            .clip(
                shape = RoundedCornerShape(12.dp)
            )
            .background(
                brush = Brush.horizontalGradient(
                    colors = listOf(
                        com.ladibells.design.ui.theme.primaryColor,
                        com.ladibells.design.ui.theme.blackColor
                    )

                )
            )
    ){
        imageUrl?.let {
            AsyncImage(
                modifier = Modifier.fillMaxSize(),
                model = imageUrl,
                contentDescription = "Banner Image",
                contentScale = ContentScale.Crop
            )
        }

        resourceValue?.let {
            ImageComponent(
                modifier = Modifier
                    .size(120.dp)
                    .padding(18.dp)
                    .align(Alignment.CenterEnd),
                resourceValue = resourceValue
            )
        }

        Column (
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(18.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Top

        ){
            title?.let {
                TextComponent(
                    modifier = Modifier
                        .wrapContentSize(),
                    textValue = title,
                    fontSizeValue = 20.sp,
                    textColorValue = Color.White,
                    fontWeightValue = FontWeight.Bold

                )
            }

            description?.let {
                TextComponent(
                    modifier = Modifier
                        .wrapContentSize(),
                    textValue = description,
                    textColorValue = Color.White,
                )
            }
        }
    }

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun BannerComponentPreview() {
    BannerComponent(
        title = "Hello World",
        description = "This is a banner component"
    )
}

@Composable
fun FestivalBannerComponent(
    title: String? = null,
    description: String? = null,
    imageUrl: String? = null,
    resourceValue: Int? = null,
    festivalBannerClicked: () -> Unit = {}
) {
    Box (
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .clickable {
                festivalBannerClicked()
            }
            .padding(12.dp)
            .clip(
                shape = RoundedCornerShape(12.dp)
            )
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(
                        Purple40, Color.Red
                    )

                )
            )
    ){
        imageUrl?.let {
            AsyncImage(
                modifier = Modifier.fillMaxSize(),
                model = imageUrl,
                contentDescription = "Banner Image",
                contentScale = ContentScale.Crop
            )
        }

        resourceValue?.let {
            ImageComponent(
                modifier = Modifier
                    .size(120.dp)
                    .padding(18.dp)
                    .align(Alignment.CenterEnd),
                resourceValue = resourceValue
            )
        }

        Column (
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(18.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Top

        ){
            title?.let {
                TextComponent(
                    modifier = Modifier
                        .wrapContentSize(),
                    textValue = title,
                    fontSizeValue = 20.sp,
                    textColorValue = Color.White,
                    fontWeightValue = FontWeight.Bold

                )
            }

            description?.let {
                TextComponent(
                    modifier = Modifier
                        .wrapContentSize(),
                    textValue = description,
                    textColorValue = Color.White,
                )
            }
        }
    }

}

@Composable
fun WeatherBannerComponent(
    cityName: String? = null,
    temperature: String? = null,
    airQualityO3: String? = null,
    imageUrl: String? = null,
    resourceValue: Int? = null,
    bannerClicked: () -> Unit = {}
) {
    Box (
        modifier = Modifier
            .fillMaxWidth()
            .defaultMinSize(minHeight = 138.dp)
            .wrapContentHeight()
            .clickable {
                bannerClicked()
            }
            .padding(12.dp)
            .clip(
                shape = RoundedCornerShape(12.dp)
            )
            .background(
                brush = Brush.horizontalGradient(
                    colors = listOf(
                        com.ladibells.design.ui.theme.primaryColor,
                        com.ladibells.design.ui.theme.blackColor
                    )

                )
            )
    ){
        imageUrl?.let {
            AsyncImage(
                modifier = Modifier.wrapContentSize()
                    .defaultMinSize(minHeight = 80.dp, minWidth = 80.dp)
                    .align(Alignment.BottomEnd),
                model = imageUrl,
                contentDescription = "Banner Image",
                contentScale = ContentScale.Crop
            )
        }

        resourceValue?.let {
            ImageComponent(
                modifier = Modifier
                    .size(120.dp)
                    .padding(18.dp)
                    .align(Alignment.CenterEnd),
                resourceValue = resourceValue
            )
        }

        Column (
            modifier = Modifier
                .fillMaxWidth()
                .matchParentSize()
                .padding(18.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.SpaceBetween

        ){
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
//                verticalAlignment = Alignment.CenterVertically
            ) {
                cityName?.let {
                    TextComponent(
                        modifier = Modifier
                            .wrapContentSize(),
                        textValue = cityName,
                        fontSizeValue = 20.sp, //or 24.sp
                        textColorValue = Color.White,
                        fontWeightValue = FontWeight.Bold

                    )
                }

                airQualityO3?.also {
                    TextComponent(
                        modifier = Modifier
                            .wrapContentSize()
                            .padding(start = 12.dp),
                        textValue = "Air Quality $it",
                        fontSizeValue = 16.sp, // 24.sp
                        textColorValue = whiteColor
                    )
                }
            }

            temperature?.let {
                TextComponent(
                    modifier = Modifier
                        .wrapContentSize(),
                    textValue = temperature,
                    textColorValue = Color.White,
                )
            }
        }
    }

}