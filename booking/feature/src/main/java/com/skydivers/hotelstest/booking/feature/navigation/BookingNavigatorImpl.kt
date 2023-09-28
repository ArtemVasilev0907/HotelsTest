package com.skydivers.hotelstest.booking.feature.navigation

import com.skydivers.hotelstest.core.navigation.MainBookingNavigator


class BookingNavigatorImpl(private val mainBookingNavigator: MainBookingNavigator):BookingNavigator {
    override fun toPayFragment() {
        mainBookingNavigator.toPayFragment()
    }
}