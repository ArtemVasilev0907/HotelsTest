package com.skydivers.hotelstest.booking.domain.usecases

import com.skydivers.hotelstest.booking.feature.UiState
import com.skydivers.hotelstest.booking.model.TouristUIModel
class AddTourist() {
    suspend operator fun invoke( state: UiState.Success): UiState.Success{
        state.data.let { booking ->
            val tourists = booking.tourists.toMutableList()
            tourists.map {
                it.isCollapsed = false
            }
            tourists.add(
                TouristUIModel()
                    .addNewFromList(tourists)
            )
            updateTourists(tourists)
            booking.bookingPriceUIModel.multiple(tourists.size)
            booking.tourists = tourists.toList()

        }
        return  state
    }
    private fun updateTourists(tourists: List<TouristUIModel>):List<TouristUIModel>{

            for (i in tourists.indices) {
                tourists[i].setNewId(i)
            }
        return tourists
    }
}



