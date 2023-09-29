package com.skydivers.hotelstest.features.rooms.presentation.di


import com.skydivers.hotelstest.features.rooms.data.di.repositoryModule
import com.skydivers.hotelstest.features.rooms.domain.di.RoomsDomainModule
import org.koin.dsl.module

val roomsModule = module {
    includes(repositoryModule, RoomsDomainModule, viewModelModule)

}