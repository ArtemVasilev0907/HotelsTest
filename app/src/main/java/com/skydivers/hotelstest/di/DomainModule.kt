package com.skydivers.locationru.di





import com.skydivers.domain.usecases.GetBookingDataUseCase
import com.skydivers.domain.usecases.GetHotelsDataUseCase
import com.skydivers.domain.usecases.GetRoomsDataUseCase
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