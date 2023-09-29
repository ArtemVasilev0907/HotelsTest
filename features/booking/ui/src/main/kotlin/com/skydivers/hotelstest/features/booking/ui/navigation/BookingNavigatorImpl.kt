package com.skydivers.hotelstest.features.booking.ui.navigation

import com.skydivers.hotelstest.core.navigation.CoreBookingNavigator


class BookingNavigatorImpl(private val coreBookingNavigator: CoreBookingNavigator):
    BookingNavigator {
    override fun toPayFragment() {
        coreBookingNavigator.toPayFragment()
    }
}