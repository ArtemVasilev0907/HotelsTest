package com.skydivers.data.models.hotels


import com.google.gson.annotations.SerializedName
import com.skydivers.domain.models.hotels.HotelsModelDomain

data class HotelsEntity(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("adress")
    val address: String,
    @SerializedName("minimal_price")
    val minimalPrice: Int,
    @SerializedName("price_for_it")
    val priceForIt: String,
    @SerializedName("rating")
    val rating: Int,
    @SerializedName("rating_name")
    val ratingName: String,
    @SerializedName("image_urls")
    val imageUrls: List<String>,
    @SerializedName("about_the_hotel")
    val aboutTheHotelEntity: AboutTheHotelEntity

){
    fun mapToDomain() : HotelsModelDomain {
        return HotelsModelDomain(
            id = id,
            name = name,
            address = address,
            minimalPrice = minimalPrice,
            priceForIt = priceForIt,
            rating = rating,
            ratingName = ratingName,
            imageUrls = imageUrls,
            aboutTheHotel = aboutTheHotelEntity.mapToDomain()
        )
            }

}