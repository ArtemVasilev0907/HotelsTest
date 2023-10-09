package com.skydivers.hotelstest.di


import com.skydivers.hotelstest.ui.fragments.hotel.HotelViewModel
import com.skydivers.hotelstest.ui.navigation.MainNavControllerProvider
import com.skydivers.hotelstest.ui.navigation.MainNavControllerProviderImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val appModule = module {

    single<MainNavControllerProvider>() {
        MainNavControllerProviderImpl()
    }






    viewModel<HotelViewModel> {
        HotelViewModel(
            getHotelsDataUseCase = get()
        )
    }


}