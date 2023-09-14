package com.skydivers.domain.usecases


import com.skydivers.domain.DataState
import com.skydivers.domain.HotelsDataRepository
import com.skydivers.domain.models.hotels.HotelsModelDomain
import com.skydivers.domain.models.rooms.RoomsModelDomain
import kotlinx.coroutines.flow.Flow

class GetRoomsDataUseCase (private val hotelsDataRepository: HotelsDataRepository) {

    suspend operator fun invoke(): Flow<DataState<RoomsModelDomain>> {
        return hotelsDataRepository.fetchRoomsDataState()

    }
}