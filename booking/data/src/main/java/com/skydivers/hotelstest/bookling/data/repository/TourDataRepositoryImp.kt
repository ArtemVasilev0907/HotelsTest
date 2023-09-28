package com.skydivers.hotelstest.bookling.data.repository

import com.skydivers.hotelstest.booking.domain.repository.TourDataRepository
import com.skydivers.hotelstest.booking.model.BookingModel
import com.skydivers.hotelstest.bookling.data.entities.TourEntity
import com.skydivers.hotelstest.bookling.data.service.BookingService
import com.skydivers.hotelstest.core.common.UiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response


internal class TourDataRepositoryImp(
    private val service: BookingService
) :
    TourDataRepository {
    override suspend fun fetchTourData(): Flow<UiState<BookingModel>> =
        flow {

            emit(fetchBookingDataFromServer().suspendMap {
                it.mapFromData()
            })


        }.flowOn(Dispatchers.IO)
            .catch {
                emit(UiState.Error(Exception(it)))
            }


    private suspend fun fetchBookingDataFromServer(): UiState<TourEntity> {
        return setState(response = service.fetchBookingData())
    }


    private fun setState(
        response: Response<TourEntity>
    ): UiState<TourEntity> {
        var uiState: UiState<TourEntity> = UiState.Loading
        try {

            if (response.code() == 200) {
                uiState = UiState.Success(response.body()!!)
            }
        } catch (e: Exception) {
            UiState.Error(
                e
            )

        }

        return uiState
    }


}

