package com.skydivers.hotelstest.di






import com.skydivers.hotelstest.core.navigation.CoreBookingNavigator
import com.skydivers.hotelstest.core.navigation.CoreRoomsNavigator
import com.skydivers.hotelstest.ui.navigation.MainBookingNavigatorImpl
import com.skydivers.hotelstest.ui.navigation.MainRoomsNavigatorImpl

import org.koin.dsl.module

val CoreModule = module {

    single<CoreBookingNavigator>{
        MainBookingNavigatorImpl(mainNavControllerProvider = get())
    }

    single<CoreRoomsNavigator> (){
        MainRoomsNavigatorImpl(mainNavControllerProvider = get())
    }









}