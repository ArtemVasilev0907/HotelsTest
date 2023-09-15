package com.skydivers.hotelstest.di





import com.skydivers.domain.usecases.*
import org.koin.dsl.module

val domainModule = module {

    factory <GetHotelsDataUseCase>{
        GetHotelsDataUseCase(hotelsDataRepository = get())
    }
    factory <GetRoomsDataUseCase>{
        GetRoomsDataUseCase(hotelsDataRepository = get())
    }

    factory <GetBookingDataUseCase>{
        GetBookingDataUseCase(hotelsDataRepository = get())
    }







}