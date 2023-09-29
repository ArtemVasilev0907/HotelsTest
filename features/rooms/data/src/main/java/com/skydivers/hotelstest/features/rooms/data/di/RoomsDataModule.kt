package com.skydivers.hotelstest.features.rooms.data.di


import com.skydivers.hotelstest.features.rooms.data.repository.RoomsDataRepositoryImp
import com.skydivers.hotelstest.features.rooms.data.service.RoomsService
import com.skydivers.hotelstest.features.rooms.domain.repository.RoomsDataRepository

import org.koin.dsl.module
import retrofit2.Retrofit

 val repositoryModule = module {

    single { get<Retrofit>().create(RoomsService::class.java) }



    single<RoomsDataRepository> {
        RoomsDataRepositoryImp(get<RoomsService>())
    }

}