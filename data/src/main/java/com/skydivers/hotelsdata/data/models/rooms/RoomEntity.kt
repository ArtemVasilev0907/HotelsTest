package com.skydivers.hotelsdata.data.models.rooms


import com.google.gson.annotations.SerializedName
import com.skydivers.domain.models.rooms.RoomModelDomain
import com.skydivers.hotelsdata.data.models.Entity

data class RoomEntity(
    @SerializedName("id")
    val id: Int,
    @SerializedName("image_urls")
    val imageUrls: List<String>,
    @SerializedName("name")
    val name: String,
    @SerializedName("peculiarities")
    val peculiarities: List<String>,
    @SerializedName("price")
    val price: Int,
    @SerializedName("price_per")
    val pricePer: String
): Entity {
    override fun mapToDomain(): RoomModelDomain {
        return RoomModelDomain(
            id = id,
            imageUrls = imageUrls,
            name = name,
            peculiarities =peculiarities,
            price = price,
            pricePer = pricePer
        )
    }
}