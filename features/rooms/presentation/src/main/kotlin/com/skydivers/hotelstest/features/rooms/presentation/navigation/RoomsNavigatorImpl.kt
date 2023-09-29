package com.skydivers.hotelstest.features.rooms.presentation.navigation

import com.skydivers.hotelstest.booking.feature.navigation.RoomsNavigator
import com.skydivers.hotelstest.core.navigation.CoreRoomsNavigator


class RoomsNavigatorImpl(private val coreRoomsNavigator: CoreRoomsNavigator): RoomsNavigator {
    override fun toPayFragment() {
        coreRoomsNavigator.toBookingFragment()
    }
}