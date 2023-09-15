package com.skydivers.hotelstest.models.booking

import com.skydivers.domain.models.booking.BookingModelDomain
import com.skydivers.hotelstest.models.BaseUIModel

data class BookingPriceUIModel(
    var tourPrice:Float,
    var serviceCharge:Float,
    var fuelCharge:Float
):BaseUIModel{
    fun BookingModelDomain.mapToPresentation():BookingPriceUIModel{
        return BookingPriceUIModel(
            tourPrice = tourPrice.toFloat(),
            serviceCharge = serviceCharge.toFloat(),
            fuelCharge = fuelCharge.toFloat()

        )
    }

    fun toPay(): Float {
        return tourPrice + serviceCharge + fuelCharge
    }

    override fun isFilled(): Boolean = true
    fun multiple(number: Int) {
        tourPrice *= number
        serviceCharge *=number
        fuelCharge *=number
    }
}
