package com.skydivers.hotelstest.booking.di

import org.koin.dsl.module

val bookingModule = module {
    includes(repositoryModule, useCaseModule, viewModelModule)

}