package com.skydivers.hotelstest.booking.model


data class BookingPriceUIModel(
    var tourPrice: Int,
    var serviceCharge: Int,
    var fuelCharge: Int
) {
    private val initTourPrice: Int = tourPrice
    private val intServiceCharge: Int = serviceCharge
    private val intFuelCharge: Int = fuelCharge


    fun toPay(): Int {
        return tourPrice + serviceCharge + fuelCharge
    }


    fun multiple(number: Int) {
        if (number < 1) return
        tourPrice = initTourPrice * number
        serviceCharge = intServiceCharge * number
        fuelCharge = intFuelCharge * number
    }
}
