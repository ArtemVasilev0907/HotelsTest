package com.skydivers.hotelstest.features.rooms.data.entities


import com.google.gson.annotations.SerializedName
import com.skydivers.hotelstest.features.rooms.domain.model.RoomsModelDomain


data class RoomsEntity(
    @SerializedName("rooms")
    val rooms: List<RoomEntity>
) {
     fun mapToDomain(): RoomsModelDomain {
        return RoomsModelDomain(
            rooms = rooms.map { it.mapToDomain() }
        )
    }


}