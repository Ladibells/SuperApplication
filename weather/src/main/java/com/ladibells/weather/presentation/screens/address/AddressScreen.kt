package com.ladibells.weather.presentation.screens.address

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.repeatOnLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ladibells.datasource.local.entity.CityItem
import com.ladibells.design.components.AppToolBar
import com.ladibells.design.components.TextComponent
import com.ladibells.design.ui.theme.backgroundColor
import com.ladibells.design.ui.theme.blackColor
import com.ladibells.design.ui.theme.greenColor
import com.ladibells.design.ui.theme.whiteColor
import com.ladibells.weather.R

@Composable
fun AddressScreen(
    primaryButtonClicked: () -> Unit = {},
    viewModel: AddressViewModel = hiltViewModel()
) {

    val state = viewModel.state.value

    Scaffold(
        topBar = {
            AppToolBar(
                title = if (state.selectedCity.isNotEmpty()) state.selectedCity else stringResource(R.string.add_address),
                isBackButtonVisible = true,
                primaryButtonClicked = {
                    primaryButtonClicked()
                }
            )
        }
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(backgroundColor)
                .padding(innerPadding)
        ) {

            AddressInstructiosn()

            DetectUserCurrentLocation()

            PopularCitiesComponent(state.popularCities, viewModel)
        }
    }
}

@Composable
fun AddressInstructiosn() {
    Column {
        TextComponent(
            modifier = Modifier.fillMaxWidth()
                .background(whiteColor)

                .wrapContentHeight()
                .padding(all = 18.dp),
            textValue = stringResource(R.string.add_address_hint),
            fontSizeValue = 18.sp,
            textColorValue = greenColor,
            fontWeightValue = FontWeight.Light
        )

        Spacer(modifier = Modifier.size(1.dp).background(backgroundColor))

    }
}

@Composable
fun DetectUserCurrentLocation() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(whiteColor)
            .wrapContentHeight()
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_location),
            contentDescription = "Location Icon"
        )

        TextComponent(
            modifier = Modifier.fillMaxWidth()
                .padding(all = 18.dp)
                .wrapContentHeight(),
            textValue = stringResource(R.string.auto_detect_my_location),
            fontSizeValue = 16.sp,
            textColorValue = blackColor,
            fontWeightValue = FontWeight.Light
        )
    }
}

@Composable
fun PopularCitiesComponent(popularCities: List<CityItem>, viewModel: AddressViewModel) {
    Column {
        TextComponent(
            modifier = Modifier.fillMaxWidth()
                .wrapContentHeight()
                .padding(16.dp),
            textValue = stringResource(R.string.add_address_hint),
            fontSizeValue = 14.sp,
            textColorValue = blackColor,
        )

        LazyVerticalGrid(
            modifier = Modifier.wrapContentSize()
                .background(whiteColor),
            columns = GridCells.Adaptive(100.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(all = 16.dp),
            content = {
                items(popularCities) { city ->
                    CityItemComponent(city = city, viewModel = viewModel)
                }
            }
        )
    }
}

@Composable
fun CityItemComponent(city: CityItem, viewModel: AddressViewModel) {
    Column(
        modifier = Modifier
            .clickable {
                viewModel.onEvent(LocationUIEvent.CityItemClicked(city.name))
            }
            .wrapContentSize()
            .defaultMinSize(100.dp)
            .border(
                width = 1.dp,
                color = if (city.isSelected) greenColor else backgroundColor,
                shape = RoundedCornerShape(12.dp)
            )
            .padding(all = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            modifier = Modifier.size(60.dp),
            painter = painterResource(city.icon),
            contentDescription = city.name,
            contentScale = ContentScale.Fit,
            colorFilter = ColorFilter.tint(if (city.isSelected) greenColor else Color.Transparent)
        )
        TextComponent(
            modifier = Modifier.wrapContentSize(),
            textValue = city.name,
            textColorValue = if (city.isSelected) greenColor else blackColor,
        )
    }
}


@Preview(showBackground = true,)
@Composable
fun AddressScreenPreview() {
    AddressScreen()
}