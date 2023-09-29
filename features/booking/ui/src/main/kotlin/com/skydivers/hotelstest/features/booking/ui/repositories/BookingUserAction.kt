package com.skydivers.hotelstest.features.booking.ui.repositories



internal sealed class BookingUserAction {

    data object AddTourist : BookingUserAction()
    data class DeleteTourist(var touristId: Int) : BookingUserAction()
    data object CheckPay : BookingUserAction()
    data object BuyTour : BookingUserAction()
    data class SaveTourist(val touristModel: com.skydivers.hotelstest.booking.model.TouristUIModel) : BookingUserAction()
}