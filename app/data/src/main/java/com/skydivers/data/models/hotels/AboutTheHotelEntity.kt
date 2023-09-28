package com.skydivers.data.models.hotels


import com.google.gson.annotations.SerializedName
import com.skydivers.domain.models.hotels.AboutTheHotelModelDomain

data class AboutTheHotelEntity(
    @SerializedName("description")
    val description: String,
    @SerializedName("peculiarities")
    val peculiarities: List<String>
) {
    fun mapToDomain(): AboutTheHotelModelDomain {
    return AboutTheHotelModelDomain(
        description = description,
        peculiarities = peculiarities
    )
    }

}