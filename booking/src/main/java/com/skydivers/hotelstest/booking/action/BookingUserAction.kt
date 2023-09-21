package com.skydivers.hotelstest.booking.action

import com.skydivers.hotelstest.booking.model.TouristUIModel


internal sealed class BookingUserAction {

    data object AddTourist : BookingUserAction()
    data class DeleteTourist(var touristId: Int) : BookingUserAction()
    data object CheckPay : BookingUserAction()
    data object BuyTour : BookingUserAction()

    data class SaveTourist(val touristModel: TouristUIModel) : BookingUserAction()
}