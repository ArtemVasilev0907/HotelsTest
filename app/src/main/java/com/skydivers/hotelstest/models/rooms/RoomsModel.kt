package com.skydivers.hotelstest.models.rooms


import com.skydivers.domain.models.rooms.RoomsModelDomain


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