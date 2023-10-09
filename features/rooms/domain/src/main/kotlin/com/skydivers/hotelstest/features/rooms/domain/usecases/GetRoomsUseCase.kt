package com.skydivers.hotelstest.features.rooms.domain.usecases


import com.skydivers.hotelstest.core.common.UiState
import com.skydivers.hotelstest.core.domain.UseCase
import com.skydivers.hotelstest.features.rooms.domain.model.RoomsModelDomain
import com.skydivers.hotelstest.features.rooms.domain.repository.RoomsDataRepository
import kotlinx.coroutines.flow.Flow

class GetRoomsUseCase(private val roomsDataRepository: RoomsDataRepository) :
    UseCase<Flow<UiState<RoomsModelDomain>>>() {

    suspend operator fun invoke(): Flow<UiState<RoomsModelDomain>> =
        executeOnBackground()

    override suspend fun executeOnBackground(): Flow<UiState<RoomsModelDomain>> =
        backgroundAsync {
            return@backgroundAsync roomsDataRepository.fetchRoomsData()
        }.await()


}