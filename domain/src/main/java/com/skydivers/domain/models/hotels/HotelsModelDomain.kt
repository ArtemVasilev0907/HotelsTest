package com.skydivers.domain.models.hotels

import com.skydivers.domain.models.ModelDomain


data class HotelsModelDomain(

    val id: Int,

    val name: String,

    val address: String,

    val minimalPrice: Int,

    val priceForIt: String,

    val rating: Int,

    val ratingName: String,

    val imageUrls: List<String>,

    val aboutTheHotel: AboutTheHotelModelDomain
): ModelDomain