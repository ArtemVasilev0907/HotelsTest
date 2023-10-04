package com.skydivers.hotelstest.features.booking.ui.di

import com.skydivers.hotelstest.features.booking.domain.di.BookingDomainModule
import com.skydivers.hotelstest.bookling.data.di.repositoryModule
import org.koin.dsl.module

val bookingModule = module {
    includes(repositoryModule, BookingDomainModule, viewModelModule)

}