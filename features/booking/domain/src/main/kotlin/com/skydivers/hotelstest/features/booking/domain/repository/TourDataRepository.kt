package com.skydivers.hotelstest.features.booking.domain.repository


import com.skydivers.hotelstest.booking.model.BookingModel
import com.skydivers.hotelstest.core.common.UiState
import kotlinx.coroutines.flow.Flow

interface TourDataRepository {
    suspend fun fetchTourData(): Flow<UiState<BookingModel>>


}