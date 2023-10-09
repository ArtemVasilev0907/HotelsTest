package com.skydivers.hotelstest.features.booking.domain.usecases


import com.skydivers.hotelstest.booking.model.BookingModel
import com.skydivers.hotelstest.booking.model.TouristUIModel
import com.skydivers.hotelstest.booking.model.TouristUIModel.Companion.addNewFromList
import com.skydivers.hotelstest.core.common.UiState


class AddTouristUseCase() {


    suspend operator fun invoke(
        state: UiState.Success<BookingModel>
    ): UiState.Success<BookingModel> = addTourist(state)


    private fun addTourist(state: UiState.Success<BookingModel>): UiState.Success<BookingModel> {

        with(state) {


            val tourists = data.tourists.toMutableList()
            val hotelId = data.id

            tourists.filter { tourist ->
                (tourist.isCollapsed)
            }.map { it.isCollapsed = false }

            tourists.add(
                TouristUIModel(hotelId = hotelId)
                    .addNewFromList(tourists)
            )
            data.bookingPriceUIModel?.multiple(tourists.size)
            data.tourists = tourists
        }
        return state
    }


}



