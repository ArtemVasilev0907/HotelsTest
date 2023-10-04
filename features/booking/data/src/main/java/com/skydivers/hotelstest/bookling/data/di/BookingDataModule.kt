package com.skydivers.hotelstest.bookling.data.di




import com.skydivers.hotelstest.features.booking.domain.repository.TourDataRepository
import com.skydivers.hotelstest.features.booking.domain.repository.ValidateEmailRepository
import com.skydivers.hotelstest.features.booking.domain.repository.ValidatePhoneRepository
import com.skydivers.hotelstest.bookling.data.repository.TourDataRepositoryImp
import com.skydivers.hotelstest.bookling.data.repository.validate.ValidateEmailImpl
import com.skydivers.hotelstest.bookling.data.repository.validate.ValidatePhoneImpl
import com.skydivers.hotelstest.bookling.data.service.BookingService

import org.koin.dsl.module
import retrofit2.Retrofit

 val repositoryModule = module {

    single { get<Retrofit>().create(BookingService::class.java) }

    single<TourDataRepository> {
        TourDataRepositoryImp(get<BookingService>())
    }

     single<ValidateEmailRepository> {
         ValidateEmailImpl()
     }

     single<ValidatePhoneRepository> {
         ValidatePhoneImpl()
     }

}