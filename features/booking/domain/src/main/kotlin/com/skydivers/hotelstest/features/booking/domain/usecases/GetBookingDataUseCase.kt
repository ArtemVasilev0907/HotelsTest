package com.skydivers.hotelstest.features.booking.domain.usecases

import com.skydivers.hotelstest.booking.model.BookingModel
import com.skydivers.hotelstest.core.common.UiState
import com.skydivers.hotelstest.core.domain.UseCase
import com.skydivers.hotelstest.features.booking.domain.repository.TourDataRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetBookingDataUseCase(private val tourDataRepository: TourDataRepository) :
    UseCase<Flow<UiState<BookingModel>>>() {

    suspend operator fun invoke(): Flow<UiState<BookingModel>> = executeOnBackground()

    override suspend fun executeOnBackground(): Flow<UiState<BookingModel>> =
        backgroundAsync { getTour() }.await()

    private fun getTour(): Flow<UiState<BookingModel>> = flow {

        tourDataRepository.fetchTourData().collect { state ->
            var uiState = state
            if (state is UiState.Success<BookingModel>) {
                uiState = UiState.Success(state.data.mapToPresentation())
            }
            emit(uiState)
        }
    }


}