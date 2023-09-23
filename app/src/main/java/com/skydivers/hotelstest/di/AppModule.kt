package com.skydivers.hotelstest.di

import com.skydivers.hotelstest.booking.navigation.BookingNavigator
import com.skydivers.hotelstest.booking.navigation.BookingNavigator2
import com.skydivers.hotelstest.ui.fragments.hotel.HotelViewModel
import com.skydivers.hotelstest.ui.fragments.rooms.RoomsViewModel
import com.skydivers.hotelstest.ui.navigation.BookingNavigatorImpl
import com.skydivers.hotelstest.ui.navigation.MainNavControllerProvider
import com.skydivers.hotelstest.ui.navigation.MainNavControllerProviderImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val appModule = module {

    single<MainNavControllerProvider>() {
        MainNavControllerProviderImpl()
    }
    single<BookingNavigator> (){
        BookingNavigatorImpl(mainNavControllerProvider = get())
    }

    single<BookingNavigator2> (){
       BookingNavigatorImpl(mainNavControllerProvider = get())
    }



    viewModel<HotelViewModel> {
        HotelViewModel(
            getHotelsDataUseCase = get()
        )
    }

    viewModel<RoomsViewModel> {
        RoomsViewModel(
            getRoomsDataUseCase = get()
        )
    }


}