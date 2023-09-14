package com.skydivers.domain.models.rooms

import com.skydivers.domain.models.ModelDomain


data class RoomsModelDomain(
    val rooms: List<RoomModelDomain>
): ModelDomain