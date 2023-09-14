package com.skydivers.hotelstest.ui.fragments.hotel

import com.skydivers.hotelstest.models.ErrorUIModel
import com.skydivers.hotelstest.models.hotel.HotelsModel


sealed interface HotelUiState {

    data class State(
        var isSuccess: Boolean = false,
        var error:ErrorUIModel? = null,
        val result: HotelsModel? = null
    ) : HotelUiState


}