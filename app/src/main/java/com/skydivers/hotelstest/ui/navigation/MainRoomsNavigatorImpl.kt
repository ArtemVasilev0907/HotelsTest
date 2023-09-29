package com.skydivers.hotelstest.ui.navigation


import com.skydivers.hotelstest.R
import com.skydivers.hotelstest.core.navigation.CoreRoomsNavigator


class MainRoomsNavigatorImpl(mainNavControllerProvider: MainNavControllerProvider) :
    CoreRoomsNavigator {
    private val navController =  mainNavControllerProvider.findNavController()
    override fun toBookingFragment() {
        navController.navigate(R.id.action_navigation_room_to_booking)
    }


}