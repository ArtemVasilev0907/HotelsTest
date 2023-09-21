package com.skydivers.hotelstest.models.hotel

import com.skydivers.domain.models.hotels.HotelsModelDomain
import com.skydivers.hotelstest.models.BaseUIModel


data class HotelsUiModel(


    val id: Int,

    val name: String,

    val address: String,

    val minimalPrice: Int,

    val priceForIt: String,

    val rating: Int,

    val ratingName: String,

    val imageUrls: List<String>,

    val aboutTheHotel: AboutTheHotel
) : BaseUIModel {
    override fun isFilled(): Boolean =true
}

fun HotelsModelDomain.mapToPresentation(): HotelsUiModel {

    return HotelsUiModel(
        id = id,
        name = name,
        address = address,
        minimalPrice = minimalPrice,
        priceForIt = priceForIt,
        rating = rating,
        ratingName = ratingName,
        imageUrls = imageUrls,
        aboutTheHotel = aboutTheHotel.mapToPresentation()

    )
}