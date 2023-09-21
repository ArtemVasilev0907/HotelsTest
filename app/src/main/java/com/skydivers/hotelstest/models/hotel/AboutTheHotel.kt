package com.skydivers.hotelstest.models.hotel

import com.skydivers.domain.models.hotels.AboutTheHotelModelDomain
import com.skydivers.hotelstest.models.BaseUIModel


data class AboutTheHotel(

    val description: String,

    val peculiarities: List<String>
):BaseUIModel {
    override fun isFilled(): Boolean = true
}

fun AboutTheHotelModelDomain.mapToPresentation():AboutTheHotel{
    return AboutTheHotel(
        description = description,
        peculiarities = peculiarities
    )
}