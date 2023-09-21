package com.skydivers.hotelstest.booking.di

import com.skydivers.hotelstest.booking.data.repository.BookingDataRepositoryImp
import com.skydivers.hotelstest.booking.usecases.BookingDataRepository
import org.koin.dsl.module

internal val repositoryModule = module{
    single<BookingDataRepository>{
        BookingDataRepositoryImp()
    }
}