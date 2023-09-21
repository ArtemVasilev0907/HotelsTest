package com.skydivers.hotelstest.models.rooms


import com.skydivers.domain.models.rooms.RoomsModelDomain
import com.skydivers.hotelstest.models.BaseUIModel


data class RoomsUiModel(
    val roomModels: List<RoomModel>
): BaseUIModel {
    override fun isFilled(): Boolean = true
}

fun RoomsModelDomain.mapToPresentation(): RoomsUiModel {
    return RoomsUiModel(
        roomModels = rooms.map {
            it.mapToPresentation()
        }
    )
}