package com.skydivers.hotelstest.di


import com.skydivers.domain.HotelsDataRepository
import com.skydivers.hotelsdata.data.repository.HotelsDataRepositoryImp


import org.koin.dsl.module


val dataModule = module {

    single<HotelsDataRepository>{
        HotelsDataRepositoryImp()
    }




}