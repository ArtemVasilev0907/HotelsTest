package com.skydivers.hotelstest.booking.feature


 sealed class UiState {
    data object Loading : UiState()
    data class Success(val data: com.skydivers.hotelstest.booking.model.BookingModel) : UiState()
    data class Error(val errorModel: com.skydivers.hotelstest.booking.model.ErrorUIModel) : UiState()
}
