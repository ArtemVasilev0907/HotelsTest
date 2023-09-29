package com.skydivers.hotelstest.features.rooms.presentation.model

import com.skydivers.hotelstest.features.rooms.domain.model.RoomModelDomain



data class RoomModel(
    val id: Int,
    val imageUrls: List<String>,
    val name: String,
    val peculiarities: List<String>,
    val price: Int,
    val pricePer: String
)

fun RoomModelDomain.mapToPresentation(): RoomModel {
    return RoomModel(
        id = id,
        imageUrls = imageUrls,
        name = name,
        peculiarities =peculiarities,
        price = price,
        pricePer = pricePer
    )
}