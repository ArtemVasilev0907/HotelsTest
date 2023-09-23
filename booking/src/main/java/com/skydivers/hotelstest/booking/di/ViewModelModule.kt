package com.skydivers.hotelstest.booking.di


import com.skydivers.hotelstest.booking.BookingViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

internal val viewModelModule = module {


    viewModel<BookingViewModel> {
        BookingViewModel(getBookingDataUseCase = get(),
            bookingNavigator = get(),
            bookingNavigator2 = get())

    }


}