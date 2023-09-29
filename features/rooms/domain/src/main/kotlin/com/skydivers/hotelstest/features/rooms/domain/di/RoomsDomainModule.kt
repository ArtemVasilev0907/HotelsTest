package com.skydivers.hotelstest.features.rooms.domain.di


import com.skydivers.hotelstest.features.rooms.domain.usecases.GetRoomsUseCase
import org.koin.dsl.module

val RoomsDomainModule = module {


    factory<GetRoomsUseCase> {
        GetRoomsUseCase(get())
    }



}