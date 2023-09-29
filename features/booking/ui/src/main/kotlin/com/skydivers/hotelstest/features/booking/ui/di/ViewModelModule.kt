package com.skydivers.hotelstest.features.booking.ui.di


import com.skydivers.hotelstest.features.booking.ui.navigation.BookingNavigator
import com.skydivers.hotelstest.features.booking.ui.navigation.BookingNavigatorImpl
import com.skydivers.hotelstest.features.booking.ui.BookingViewModel
import com.skydivers.hotelstest.core.navigation.CoreBookingNavigator


import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

internal val viewModelModule = module {


    viewModel {
        BookingViewModel(
            getBookingDataUseCase = get(),
            addTouristUseCase = get(),
            deleteTouristUseCase = get(),
            bookingNavigator = get()
        )

    }

    single<BookingNavigator> (){
        BookingNavigatorImpl(get<CoreBookingNavigator>())
    }


}