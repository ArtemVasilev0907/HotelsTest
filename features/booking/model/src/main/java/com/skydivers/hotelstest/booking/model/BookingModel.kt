package com.skydivers.hotelstest.booking.model


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
    var tourists: MutableList<TouristUIModel>? = mutableListOf(),
    var buyerInfo: BuyerInfoUIModel? = BuyerInfoUIModel(),
    var addTouristUIModel: AddTouristUIModel? = AddTouristUIModel(),
    var bookingPriceUIModel: BookingPriceUIModel?=null
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
            tourists = mutableListOf(),
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

