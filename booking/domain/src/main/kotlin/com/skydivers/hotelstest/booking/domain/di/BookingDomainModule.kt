package com.skydivers.hotelstest.booking.domain.di

import com.skydivers.hotelstest.booking.domain.usecases.AddTourist
import com.skydivers.hotelstest.booking.domain.usecases.DeleteTouristUseCase
import com.skydivers.hotelstest.booking.domain.usecases.GetBookingDataUseCase
import org.koin.dsl.module

val BookingDomainModule = module {


    factory<GetBookingDataUseCase> {
        GetBookingDataUseCase(get())
    }
    factory {
        AddTourist()
    }
    factory {
        DeleteTouristUseCase()
    }


}