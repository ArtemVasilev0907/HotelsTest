package com.skydivers.hotelstest.booking.usecases


import android.util.Log
import com.skydivers.hotelstest.booking.UiState
import kotlinx.coroutines.flow.Flow

internal class GetBookingDataUseCase(private val bookingDataRepository: BookingDataRepository) {

    suspend operator fun invoke(): Flow<UiState> {
        Log.e("UseCase", "invoke")
        return bookingDataRepository.fetchBookingDataState()

    }


}