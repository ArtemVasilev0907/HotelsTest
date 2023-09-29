package com.skydivers.hotelstest.features.rooms.domain.repository


import com.skydivers.hotelstest.features.rooms.domain.model.RoomsModelDomain
import com.skydivers.hotelstest.core.common.UiState
import kotlinx.coroutines.flow.Flow

interface RoomsDataRepository {
    suspend fun fetchRoomsData(): Flow<UiState<RoomsModelDomain>>


}