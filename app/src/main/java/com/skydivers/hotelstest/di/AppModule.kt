package com.skydivers.hotelstest.di

import com.skydivers.hotelstest.ui.fragments.hotel.HotelViewModel
import com.skydivers.hotelstest.ui.fragments.rooms.RoomsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val appModule = module {


    viewModel<HotelViewModel>{
        HotelViewModel(
            getHotelsDataUseCase = get()

        )

    }
    viewModel<RoomsViewModel>{
        RoomsViewModel(
            getRoomsDataUseCase = get()
        )
    }











}