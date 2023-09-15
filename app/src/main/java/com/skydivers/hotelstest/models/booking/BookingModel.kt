package com.skydivers.hotelstest.models.booking

import com.skydivers.domain.models.booking.BookingModelDomain
import com.skydivers.hotelstest.models.BaseUIModel


data class BookingModel(
    val arrivalCountry: String,
    val departure: String,
    val fuelCharge: Int,
    val horating: Int,
    val hotelAdress: String,
    val hotelName: String,
    val id: Int,
    val numberOfNights: Int,
    val nutrition: String,
    val ratingName: String,
    val room: String,
    val serviceCharge: Int,
    val tourDateStart: String,
    val tourDateStop: String,
    val tourPrice: Int,
    var tourists: List<TouristUIModel> = listOf(),
    val buyerInfo: BuyerInfoUIModel = BuyerInfoUIModel(),
    val addTouristUIModel: AddTouristUIModel = AddTouristUIModel(),
    val bookingPriceUIModel: BookingPriceUIModel
) : BaseUIModel{
    override fun isFilled(): Boolean = true

}

fun BookingModelDomain.mapToPresentation(): BookingModel {
    return BookingModel(
        arrivalCountry = arrivalCountry,
        departure = departure,
        fuelCharge = fuelCharge,
        horating = horating,
        hotelAdress = hotelAdress,
        hotelName = hotelName,
        id = id,
        numberOfNights = numberOfNights,
        nutrition = nutrition,
        ratingName = ratingName,
        room = room,
        serviceCharge = serviceCharge,
        tourDateStart = tourDateStart,
        tourDateStop = tourDateStop,
        tourPrice = tourPrice,
        bookingPriceUIModel = mapToBookingUIPayPresentation(),




    )
}
fun BookingModelDomain.mapToBookingUIPayPresentation():BookingPriceUIModel{
    return BookingPriceUIModel(
        tourPrice = tourPrice.toFloat(),
        serviceCharge = serviceCharge.toFloat(),
        fuelCharge = fuelCharge.toFloat()

    )
}