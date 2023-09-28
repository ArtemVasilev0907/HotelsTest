package com.skydivers.hotelstest.di






import com.skydivers.hotelstest.core.navigation.MainBookingNavigator
import com.skydivers.hotelstest.ui.navigation.MainBookingNavigatorImpl

import org.koin.dsl.module

val CoreModule = module {

    single<MainBookingNavigator>{
        MainBookingNavigatorImpl(get())
    }









}