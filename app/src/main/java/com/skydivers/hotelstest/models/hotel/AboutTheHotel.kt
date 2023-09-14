package com.skydivers.hotelstest.models.hotel

import com.skydivers.domain.models.hotels.AboutTheHotelModelDomain


data class AboutTheHotel(

    val description: String,

    val peculiarities: List<String>
)

fun AboutTheHotelModelDomain.mapToPresentation():AboutTheHotel{
    return AboutTheHotel(
        description = description,
        peculiarities = peculiarities
    )
}