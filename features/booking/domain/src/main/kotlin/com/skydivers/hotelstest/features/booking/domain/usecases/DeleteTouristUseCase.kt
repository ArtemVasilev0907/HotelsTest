package com.skydivers.hotelstest.features.booking.domain.usecases


import com.skydivers.hotelstest.booking.model.BookingModel
import com.skydivers.hotelstest.booking.model.TouristUIModel
import com.skydivers.hotelstest.core.common.UiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

class DeleteTouristUseCase() {
    suspend operator fun invoke(state: UiState.Success<BookingModel>, index:Int): UiState.Success<BookingModel>{

            state.data.let { booking ->
                runBlocking {
                    withContext(Dispatchers.IO){
                        val tourists = booking.tourists.toMutableList()
                        tourists.indexOfFirst { it.id == index }.let {
                            tourists.removeAt(it)
                        }
                        updateTourists(tourists, index)
                        booking.bookingPriceUIModel?.multiple(tourists.size)
                        booking.tourists = tourists
                    }
                }

                return  state
            }

    }
    private fun updateTourists(tourists: MutableList<TouristUIModel>, index:Int):List<TouristUIModel>{

        tourists.filter {tourist->
            (tourist.id >= index)
        }.mapIndexed { i, touristUIModel ->
            touristUIModel.setNewId(i+index-1)
        }

        return tourists
    }
}



