package com.skydivers.hotelstest.di


import com.skydivers.hotelstest.core.navigation.CoreBookingNavigator
import com.skydivers.hotelstest.core.navigation.CoreRoomsNavigator
import com.skydivers.hotelstest.ui.fragments.hotel.HotelViewModel

import com.skydivers.hotelstest.ui.navigation.MainBookingNavigatorImpl

import com.skydivers.hotelstest.ui.navigation.MainNavControllerProvider
import com.skydivers.hotelstest.ui.navigation.MainNavControllerProviderImpl
import com.skydivers.hotelstest.ui.navigation.MainRoomsNavigatorImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val appModule = module {

    single<MainNavControllerProvider>() {
        MainNavControllerProviderImpl()
    }
    single<CoreBookingNavigator> (){
        MainBookingNavigatorImpl(mainNavControllerProvider = get())
    }

    single<CoreRoomsNavigator> (){
        MainRoomsNavigatorImpl(mainNavControllerProvider = get())
    }





    viewModel<HotelViewModel> {
        HotelViewModel(
            getHotelsDataUseCase = get()
        )
    }




}