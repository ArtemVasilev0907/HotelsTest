package com.skydivers.hotelstest.ui.navigation

import android.util.Log
import com.skydivers.hotelstest.R
import com.skydivers.hotelstest.booking.navigation.BookingNavigator
import com.skydivers.hotelstest.booking.navigation.BookingNavigator2

class BookingNavigatorImpl(mainNavControllerProvider: MainNavControllerProvider) : BookingNavigator, BookingNavigator2 {
    private val navController =  mainNavControllerProvider.findNavController()
    override fun toPayFragment() {
        navController.navigate(R.id.action_navigation_booking_to_paid)
    }

    override fun toPayFragment2() {
        Log.e("navigator", BookingNavigator2::class.simpleName.toString())
    }
}