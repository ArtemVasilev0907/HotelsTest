package com.skydivers.hotelstest.booking.usecases


import com.skydivers.hotelstest.booking.UiState
import kotlinx.coroutines.flow.Flow

internal interface BookingDataRepository {
    suspend fun fetchBookingDataState(): Flow<UiState>
    suspend fun fetchDataState(): Flow<UiState>


}