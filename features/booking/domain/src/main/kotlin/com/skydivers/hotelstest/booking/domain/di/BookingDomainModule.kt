package com.skydivers.hotelstest.booking.domain.di

import com.skydivers.hotelstest.booking.domain.usecases.AddTouristUseCase
import com.skydivers.hotelstest.booking.domain.usecases.DeleteTouristUseCase
import com.skydivers.hotelstest.booking.domain.usecases.GetBookingDataUseCase
import com.skydivers.hotelstest.booking.domain.usecases.validation.ValidateEmailUseCase
import com.skydivers.hotelstest.booking.domain.usecases.validation.ValidatePhoneUseCase
import org.koin.dsl.module

val BookingDomainModule = module {


    factory<GetBookingDataUseCase> {
        GetBookingDataUseCase(get())
    }
    factory {
        AddTouristUseCase()
    }
    factory {
        DeleteTouristUseCase()
    }

    factory {
        ValidatePhoneUseCase(get())
    }

    factory {
        ValidateEmailUseCase(get())
    }


}