package com.skydivers.domain.usecases


import com.skydivers.domain.DataState
import com.skydivers.domain.HotelsDataRepository
import com.skydivers.domain.models.rooms.RoomsModelDomain
import kotlinx.coroutines.flow.Flow

class GetRoomsDataUseCase (private val hotelsDataRepository: HotelsDataRepository):UseCase<Flow<DataState<RoomsModelDomain>>>() {

    suspend operator fun invoke(): Flow<DataState<RoomsModelDomain>> {
        return executeOnBackground()

    }

    override suspend fun executeOnBackground(): Flow<DataState<RoomsModelDomain>>  =
        backgroundAsync {
            return@backgroundAsync hotelsDataRepository.fetchRoomsDataState()
        }.await()


}