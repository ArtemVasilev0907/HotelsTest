package com.skydivers.hotelsdata.data.models.rooms


import com.google.gson.annotations.SerializedName
import com.skydivers.domain.models.rooms.RoomsModelDomain
import com.skydivers.hotelsdata.data.models.Entity

data class RoomsEntity(
    @SerializedName("rooms")
    val rooms: List<RoomEntity>
) : Entity {
    override fun mapToDomain(): RoomsModelDomain {
        return RoomsModelDomain(
            rooms = rooms.map { it.mapToDomain() }
        )
    }


}