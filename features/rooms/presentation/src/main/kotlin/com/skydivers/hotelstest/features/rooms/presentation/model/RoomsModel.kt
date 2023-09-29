package com.skydivers.hotelstest.features.rooms.presentation.model


import com.skydivers.hotelstest.features.rooms.domain.model.RoomsModelDomain



data class RoomsModel(
    val roomModels: List<RoomModel>
)

fun RoomsModelDomain.mapToPresentation(): RoomsModel {
    return RoomsModel(
        roomModels = rooms.map {
            it.mapToPresentation()
        }
    )
}