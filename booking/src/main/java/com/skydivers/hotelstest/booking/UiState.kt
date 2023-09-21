package com.skydivers.hotelstest.booking


import com.skydivers.hotelstest.booking.model.BookingModel
import com.skydivers.hotelstest.booking.model.ErrorUIModel


internal sealed class UiState {
    data object Loading : UiState()
    data class Success(val data: BookingModel) : UiState()
    data class Error(val errorModel: ErrorUIModel) : UiState()
}