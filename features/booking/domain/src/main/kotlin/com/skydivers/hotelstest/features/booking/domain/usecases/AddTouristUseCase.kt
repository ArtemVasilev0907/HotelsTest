package com.skydivers.hotelstest.features.booking.domain.usecases


import com.skydivers.hotelstest.booking.model.BookingModel
import com.skydivers.hotelstest.booking.model.TouristUIModel
import com.skydivers.hotelstest.core.common.UiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

class AddTouristUseCase() {
    suspend operator fun invoke( state: UiState.Success<BookingModel>): UiState.Success<BookingModel>{
        with(state){

            runBlocking {
                withContext(Dispatchers.IO){
                    val tourists = data.tourists.toMutableList()
                    tourists.filter {tourist->
                        (tourist.isCollapsed)
                    }.let { filteredTourists ->
                        filteredTourists.map { it.isCollapsed  =  false}
                    }
                    tourists.add(
                        TouristUIModel()
                            .addNewFromList(tourists))
                    // updateTourists(tourists!!)
                    data.bookingPriceUIModel?.multiple(tourists.size)
                    data.tourists = tourists
                }
            }
        }
            return state

    }
}



