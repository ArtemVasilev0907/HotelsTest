package com.skydivers.domain.models.rooms

import com.skydivers.domain.models.ModelDomain


data class RoomModelDomain(
    val id: Int,
    val imageUrls: List<String>,
    val name: String,
    val peculiarities: List<String>,
    val price: Int,
    val pricePer: String
): ModelDomain