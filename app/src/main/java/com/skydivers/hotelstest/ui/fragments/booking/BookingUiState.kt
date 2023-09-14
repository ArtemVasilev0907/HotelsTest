package com.skydivers.hotelstest.ui.fragments.booking

import com.skydivers.hotelstest.models.booking.BookingModel



sealed interface BookingUiState {

    data class State(
        var isSuccess: Boolean = false,

        val result: BookingModel? = null
    ) : BookingUiState


}