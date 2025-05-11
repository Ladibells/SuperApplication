package com.ladibells.festival.presentation.screens

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ladibells.festival.domain.usecase.GetFestivalDataUseCase
import com.ladibells.utilities.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach


@HiltViewModel
class FestivalViewModel @Inject constructor(
    private val getFestivalDataUseCase: GetFestivalDataUseCase,
) : ViewModel() {

    private val _state = mutableStateOf(FestivalHomeScreenState())
    val state: State<FestivalHomeScreenState> = _state

    private fun getFestivalsData() {
        viewModelScope.launch {
            getFestivalDataUseCase().onEach { result ->
                when (result) {
                    is Resource.Error -> {
                        _state.value = FestivalHomeScreenState(
                            error = result.message ?: "An unexpected error occurred",
                            isLoading = false
                        )
                    }

                    is Resource.Loading -> {
                        _state.value = FestivalHomeScreenState(isLoading = true)
                    }

                    is Resource.Success -> {
                        if (result.data != null) {
                            _state.value = FestivalHomeScreenState(
                                isLoading = false,
                                festivalName = result.data?.festivalName ?: "",
                                festivalDate = result.data?.festivalDate ?: "",
                                festivalDescription = result.data?.festivalDescription ?: ""
                            )
                        }
                    }
                }
            }.launchIn(viewModelScope)
        }
    }
}