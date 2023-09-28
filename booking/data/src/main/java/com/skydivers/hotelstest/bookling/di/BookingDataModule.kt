package com.skydivers.hotelstest.bookling.di




import com.skydivers.hotelstest.booking.domain.repository.TourDataRepository
import com.skydivers.hotelstest.bookling.data.repository.TourDataRepositoryImp
import com.skydivers.hotelstest.bookling.data.service.BookingService

import org.koin.dsl.module
import retrofit2.Retrofit

 val repositoryModule = module {

    single { get<Retrofit>().create(BookingService::class.java) }



    single<TourDataRepository> {
        TourDataRepositoryImp(get<BookingService>())
    }

}