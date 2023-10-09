package com.skydivers.hotelstest.bookling.data.repository

import com.skydivers.hotelstest.booking.model.BookingModel
import com.skydivers.hotelstest.bookling.data.service.BookingService
import com.skydivers.hotelstest.core.common.UiState
import com.skydivers.hotelstest.core.common.toFlow
import com.skydivers.hotelstest.core.data.repository.AsyncRepository
import com.skydivers.hotelstest.core.network.retrofit.toUIState
import com.skydivers.hotelstest.features.booking.domain.repository.TourDataRepository
import kotlinx.coroutines.flow.Flow


internal class TourDataRepositoryImp(
    private val service: BookingService
) : AsyncRepository(),
    TourDataRepository {

    override suspend fun fetchTourData(): Flow<UiState<BookingModel>> =
        doBackgroundAsync {
            service.getData().toUIState().suspendMap {
                it.mapFromData()
            }.toFlow()
        }.await()


}

