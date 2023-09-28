package com.skydivers.hotelstest.booking.model

import com.google.gson.annotations.SerializedName


data class BookingModel(

    @SerializedName("arrival_country")
    val arrivalCountry: String,
    @SerializedName("departure")
    val departure: String,
    @SerializedName("fuel_charge")
    val fuelCharge: Int,
    @SerializedName("horating")
    val horating: Int,
    @SerializedName("hotel_adress")
    val hotelAdress: String,
    @SerializedName("hotel_name")
    val hotelName: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("number_of_nights")
    val numberOfNights: Int,
    @SerializedName("nutrition")
    val nutrition: String,
    @SerializedName("rating_name")
    val ratingName: String,
    @SerializedName("room")
    val room: String,
    @SerializedName("service_charge")
    val serviceCharge: Int,
    @SerializedName("tour_date_start")
    val tourDateStart: String,
    @SerializedName("tour_date_stop")
    val tourDateStop: String,
    @SerializedName("tour_price")
    val tourPrice: Int,
    var tourists: List<TouristUIModel> = listOf(),
    var buyerInfo: BuyerInfoUIModel = BuyerInfoUIModel(),
    var addTouristUIModel: AddTouristUIModel = AddTouristUIModel(),
    var bookingPriceUIModel: BookingPriceUIModel
) {
    fun mapToPresentation(): BookingModel =
        BookingModel(
            arrivalCountry,
            departure,
            fuelCharge,
            horating,
            hotelAdress,
            hotelName,
            id,
            numberOfNights,
            nutrition,
            ratingName,
            room,
            serviceCharge,
            tourDateStart,
            tourDateStop,
            tourPrice,
            bookingPriceUIModel = createPriceModel(),
            tourists = listOf(),
            addTouristUIModel = AddTouristUIModel(),
            buyerInfo = BuyerInfoUIModel()
        )




    private fun createPriceModel(): BookingPriceUIModel {
        return BookingPriceUIModel(
            tourPrice = tourPrice,
            serviceCharge = serviceCharge,
            fuelCharge = fuelCharge

        )
    }
}

