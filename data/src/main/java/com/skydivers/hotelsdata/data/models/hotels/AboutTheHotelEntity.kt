package com.skydivers.hotelsdata.data.models.hotels


import com.google.gson.annotations.SerializedName
import com.skydivers.domain.models.hotels.AboutTheHotelModelDomain
import com.skydivers.hotelsdata.data.models.Entity

data class AboutTheHotelEntity(
    @SerializedName("description")
    val description: String,
    @SerializedName("peculiarities")
    val peculiarities: List<String>
) :Entity{
    override fun mapToDomain(): AboutTheHotelModelDomain {
    return AboutTheHotelModelDomain(
        description = description,
        peculiarities = peculiarities
    )
    }
}