package com.skydivers.domain.usecases


import com.skydivers.domain.DataState
import com.skydivers.domain.HotelsDataRepository
import com.skydivers.domain.models.booking.BookingModelDomain
import kotlinx.coroutines.flow.Flow

class GetBookingDataUseCase (private val hotelsDataRepository: HotelsDataRepository) {

    suspend operator fun invoke(): Flow<DataState<BookingModelDomain>> {
        return hotelsDataRepository.fetchBookingDataState()

    }
}