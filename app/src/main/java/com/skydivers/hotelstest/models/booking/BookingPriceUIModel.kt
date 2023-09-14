package com.skydivers.hotelstest.models.booking

import com.skydivers.domain.models.booking.BookingModelDomain
import com.skydivers.hotelstest.models.BaseUIModel

data class BookingPriceUIModel(
    val tourPrice:Float,
    val  serviceCharge:Float,
    val fuelCharge:Float
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
}
