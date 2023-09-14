package com.skydivers.hotelstest.ui.action

import com.skydivers.hotelstest.models.booking.TouristUIModel

sealed class BookingUserAction {

    object AddTourist : BookingUserAction()
    object DeleteTourist : BookingUserAction()
    object CheckPay: BookingUserAction()
    object BuyTour: BookingUserAction()
    data class SaveTourist(val touristModel: TouristUIModel) : BookingUserAction()
}