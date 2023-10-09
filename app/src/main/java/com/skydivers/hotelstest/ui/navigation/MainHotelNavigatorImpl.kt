package com.skydivers.hotelstest.ui.navigation


import android.os.Bundle
import com.skydivers.hotelstest.R
import com.skydivers.hotelstest.core.navigation.CoreHotelNavigator



class MainHotelNavigatorImpl(mainNavControllerProvider: MainNavControllerProvider) :
    CoreHotelNavigator {
    private val navController =  mainNavControllerProvider.findNavController()
    override fun toRoomsFragment(hotel:String) {
        val hotelNameBundle = Bundle()
        hotelNameBundle.putString("hotelName", hotel)

        navController.navigate(R.id.action_navigation_hotel_to_room, hotelNameBundle)
    }


}