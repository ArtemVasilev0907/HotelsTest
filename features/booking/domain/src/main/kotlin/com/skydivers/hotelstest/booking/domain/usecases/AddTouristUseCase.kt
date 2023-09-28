package com.skydivers.hotelstest.booking.domain.usecases


import com.skydivers.hotelstest.booking.model.BookingModel
import com.skydivers.hotelstest.booking.model.TouristUIModel
import com.skydivers.hotelstest.core.common.UiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

class AddTouristUseCase() {
    suspend operator fun invoke( state: UiState.Success<BookingModel>): UiState.Success<BookingModel>{
        state.data.let { booking ->
            val tourists = booking.tourists?.toMutableList()
            tourists?.map {
                it.isCollapsed = false
            }

            runBlocking {
                withContext(Dispatchers.IO){
                    tourists?.add(
                        TouristUIModel()
                            .addNewFromList(tourists))
                   // updateTourists(tourists!!)
                    booking.bookingPriceUIModel?.multiple(tourists!!.size)
                    booking.tourists = tourists
                }
            }
            return state

        }

    }
}



