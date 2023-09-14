package com.skydivers.domain.usecases


import com.skydivers.domain.DataState
import com.skydivers.domain.HotelsDataRepository
import com.skydivers.domain.models.hotels.HotelsModelDomain
import kotlinx.coroutines.flow.Flow

class GetHotelsDataUseCase (private val hotelsDataRepository: HotelsDataRepository) {

    suspend operator fun invoke(): Flow<DataState<HotelsModelDomain>> {
        return hotelsDataRepository.fetchHotelDataState()

    }
}