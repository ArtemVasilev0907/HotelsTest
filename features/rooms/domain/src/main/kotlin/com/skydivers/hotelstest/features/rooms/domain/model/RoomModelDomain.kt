package com.skydivers.hotelstest.features.rooms.domain.model




data class RoomModelDomain(
    val id: Int,
    val imageUrls: List<String>,
    val name: String,
    val peculiarities: List<String>,
    val price: Int,
    val pricePer: String
)