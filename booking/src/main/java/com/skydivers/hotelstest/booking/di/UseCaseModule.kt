package com.skydivers.hotelstest.booking.di

import com.skydivers.hotelstest.booking.usecases.GetBookingDataUseCase
import org.koin.dsl.module

internal val useCaseModule = module {
    factory <GetBookingDataUseCase>{
        GetBookingDataUseCase(bookingDataRepository = get())
    }
}