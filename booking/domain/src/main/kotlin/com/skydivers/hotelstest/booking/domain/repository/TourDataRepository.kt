package com.skydivers.hotelstest.booking.domain.repository



import com.skydivers.hotelstest.booking.feature.UiState
import kotlinx.coroutines.flow.Flow

interface TourDataRepository {
    suspend fun fetchTourData(): Flow<UiState>



}