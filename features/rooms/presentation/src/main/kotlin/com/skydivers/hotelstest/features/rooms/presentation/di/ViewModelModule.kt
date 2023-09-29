package com.skydivers.hotelstest.features.rooms.presentation.di


import com.skydivers.hotelstest.booking.feature.navigation.RoomsNavigator
import com.skydivers.hotelstest.features.rooms.presentation.navigation.RoomsNavigatorImpl
import com.skydivers.hotelstest.core.navigation.CoreRoomsNavigator
import com.skydivers.hotelstest.features.rooms.presentation.fragment.RoomsViewModel


import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

internal val viewModelModule = module {


    viewModel {
        RoomsViewModel(
            getRoomsUseCase = get(),
            roomsNavigator = get()
        )

    }

    single<RoomsNavigator> (){
        RoomsNavigatorImpl(get<CoreRoomsNavigator>())
    }


}