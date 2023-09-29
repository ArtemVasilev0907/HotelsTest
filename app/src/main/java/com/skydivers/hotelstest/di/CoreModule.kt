package com.skydivers.hotelstest.di






import com.skydivers.hotelstest.core.navigation.CoreBookingNavigator
import com.skydivers.hotelstest.ui.navigation.MainBookingNavigatorImpl

import org.koin.dsl.module

val CoreModule = module {

    single<CoreBookingNavigator>{
        MainBookingNavigatorImpl(get())
    }









}