package com.skydivers.hotelstest.ui.fragments.rooms

import com.skydivers.hotelstest.models.rooms.RoomsModel


sealed interface RoomUiState {

    data class State(
        var isSuccess: Boolean = false,

        val result: RoomsModel? = null
    ) : RoomUiState


}