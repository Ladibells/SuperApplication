package com.ladibells.superapplication.presentation.navigation

import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.navigation3.runtime.entry
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay
import com.ladibells.festival.presentation.screens.FestivalHomeScreen
import com.ladibells.superapplication.presentation.screens.home.HomeScreen
import com.ladibells.wealth.presentation.screens.details.CoinDetailsScreen
import com.ladibells.wealth.presentation.screens.wealthhome.WealthHomeScreen
import com.ladibells.weather.presentation.screens.address.AddressScreen
import com.ladibells.weather.presentation.screens.weather_home.WeatherHomeScreen

@Composable
fun Navigation3Setup() {
    val backStack = remember { mutableStateListOf<Any>(HomeScreenKey) }

    NavDisplay(
        backStack = backStack,
        onBack = { backStack.removeLastOrNull() },
        entryProvider = entryProvider {
            entry<HomeScreenKey> {
                HomeScreen(
                    primaryButtonClicked = {
                        backStack.add(WealthHomeScreenKey)
                    },
                    wealthBannerClicked = {
                        backStack.add(WealthHomeScreenKey)
                    },
                    festivalBannerClicked = {
                        backStack.add(FestivalHomeScreenKey)
                    },
                    addAddressClicked = {
                        backStack.add(AddressScreenKey)
                    },
                    weatherBannerClicked = {
                        backStack.add(WeatherHomeScreenKey)
                    }
                )
            }

            entry<WealthHomeScreenKey> {
                WealthHomeScreen(
                    primaryButtonClicked = {
                        backStack.removeLastOrNull()
                    },
                    coinItemClicked = { coinId ->
                        backStack.add(CoinDetailsScreenKey(coinId))
                    }
                )
            }

            entry<CoinDetailsScreenKey> { key ->
                CoinDetailsScreen(
                    key.coinId,
                    backButtonClicked = {
                        backStack.removeLastOrNull()
                    }
                )
            }

            entry<FestivalHomeScreenKey> {
                FestivalHomeScreen(
                    primaryButtonClicked = {
                        backStack.removeLastOrNull()
                    }
                )
            }

            entry<AddressScreenKey> {
                AddressScreen(
                    primaryButtonClicked = {
                        backStack.removeLastOrNull()
                    }
                )
            }

            entry<WeatherHomeScreenKey> {
                WeatherHomeScreen(
                    primaryButtonClicked = {
                        backStack.removeLastOrNull()
                    }
                )
            }
        },
        transitionSpec = {
            // Want to horizontally from right to left
            slideInHorizontally(initialOffsetX = { it }) togetherWith slideOutHorizontally(targetOffsetX = { -it })
        },
        popTransitionSpec = {
            // Want to horizontally from left to right
            slideInHorizontally(initialOffsetX = { -it }) togetherWith slideOutHorizontally(targetOffsetX = { it })
        }
    )
}

data object HomeScreenKey
data object WealthHomeScreenKey
data class CoinDetailsScreenKey(val coinId: String)
data object FestivalHomeScreenKey
data object AddressScreenKey
data object WeatherHomeScreenKey