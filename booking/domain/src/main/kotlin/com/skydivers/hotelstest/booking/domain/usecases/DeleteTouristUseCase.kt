package com.skydivers.hotelstest.booking.domain.usecases


import com.skydivers.hotelstest.booking.model.BookingModel
import com.skydivers.hotelstest.booking.model.TouristUIModel
import com.skydivers.hotelstest.core.common.UiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

class DeleteTouristUseCase() {
    suspend operator fun invoke(state: UiState.Success<BookingModel>, index:Int): UiState.Success<BookingModel>{


            state.data.let { booking ->
                val tourists = booking.tourists?.toMutableList()
                tourists?.indexOfFirst { it.id == index }.let {
                    tourists?.removeAt(it!!)
                }
                runBlocking {
                    withContext(Dispatchers.IO){
                        updateTourists(tourists!!)
                        booking.bookingPriceUIModel?.multiple(tourists.size)
                        booking.tourists = tourists
                    }
                }


                return  state
            }

    }
    private fun updateTourists(tourists: MutableList<TouristUIModel>):List<TouristUIModel>{

//            for (i in tourists.indices) {
//                tourists[i].setNewId(i)
//            }
        tourists.mapIndexed { index, touristUIModel ->
            touristUIModel.setNewId(index)
        }
        return tourists
    }
}



