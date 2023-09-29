package com.skydivers.hotelstest.features.rooms.domain.usecases



import com.skydivers.hotelstest.features.rooms.domain.model.RoomsModelDomain
import com.skydivers.hotelstest.core.common.UiState
import com.skydivers.hotelstest.features.rooms.domain.repository.RoomsDataRepository
import kotlinx.coroutines.flow.Flow

class GetRoomsUseCase (private val roomsDataRepository: RoomsDataRepository) {

    suspend operator fun invoke(): Flow<UiState<RoomsModelDomain>> {
        return roomsDataRepository.fetchRoomsData()

    }


}