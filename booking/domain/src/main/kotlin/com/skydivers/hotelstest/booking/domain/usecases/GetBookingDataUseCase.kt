package com.skydivers.hotelstest.booking.domain.usecases




import com.skydivers.hotelstest.booking.domain.repository.TourDataRepository
import com.skydivers.hotelstest.booking.feature.UiState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow

class GetBookingDataUseCase(private val tourDataRepository: TourDataRepository) {

    suspend operator fun invoke(): Flow<UiState> = flow {

        tourDataRepository.fetchTourData().collect { state ->
            var uiState = state
            if (state is UiState.Success) {
                uiState = UiState.Success(state.data.mapToPresentation())
            }
            emit(uiState)
        }

    }


}