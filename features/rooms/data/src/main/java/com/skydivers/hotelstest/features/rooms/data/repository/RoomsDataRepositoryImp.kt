package com.skydivers.hotelstest.features.rooms.data.repository


import com.skydivers.hotelstest.core.common.UiState
import com.skydivers.hotelstest.core.common.toFlow
import com.skydivers.hotelstest.core.data.repository.AsyncRepository
import com.skydivers.hotelstest.core.network.retrofit.toUIState
import com.skydivers.hotelstest.features.rooms.data.service.RoomsService
import com.skydivers.hotelstest.features.rooms.domain.model.RoomsModelDomain
import com.skydivers.hotelstest.features.rooms.domain.repository.RoomsDataRepository

import kotlinx.coroutines.flow.Flow



internal class RoomsDataRepositoryImp(
    private val service: RoomsService
) :
    AsyncRepository(),RoomsDataRepository {

    override suspend fun fetchRoomsData(): Flow<UiState<RoomsModelDomain>> =
        doBackgroundAsync {
            service.getData().toUIState().suspendMap {
                it.mapToDomain()
            }.toFlow()
        }.await()



}

