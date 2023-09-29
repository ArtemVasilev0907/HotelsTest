package com.skydivers.hotelstest.ui.navigation


import com.skydivers.hotelstest.R
import com.skydivers.hotelstest.core.navigation.CoreBookingNavigator


class MainBookingNavigatorImpl(mainNavControllerProvider: MainNavControllerProvider) :
    CoreBookingNavigator {
    private val navController =  mainNavControllerProvider.findNavController()
    override fun toPayFragment() {
        navController.navigate(R.id.action_navigation_booking_to_paid)
    }


}