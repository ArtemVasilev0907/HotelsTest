package com.skydivers.hotelstest.booking.ui.di


import com.skydivers.hotelstest.booking.feature.navigation.BookingNavigator
import com.skydivers.hotelstest.booking.feature.navigation.BookingNavigatorImpl
import com.skydivers.hotelstest.booking.ui.BookingViewModel
import com.skydivers.hotelstest.core.navigation.MainBookingNavigator


import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

internal val viewModelModule = module {


    viewModel {
        BookingViewModel(
            getBookingDataUseCase = get(),
            addTourist = get(),
            deleteTouristUseCase = get(),
            bookingNavigator = get()
        )

    }

    single<BookingNavigator> (){
        BookingNavigatorImpl(get<MainBookingNavigator>())
    }


}