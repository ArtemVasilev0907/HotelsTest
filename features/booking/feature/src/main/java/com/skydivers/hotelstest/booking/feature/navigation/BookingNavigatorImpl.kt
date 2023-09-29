package com.skydivers.hotelstest.booking.feature.navigation

import com.skydivers.hotelstest.core.navigation.CoreBookingNavigator


class BookingNavigatorImpl(private val coreBookingNavigator: CoreBookingNavigator):BookingNavigator {
    override fun toPayFragment() {
        coreBookingNavigator.toPayFragment()
    }
}