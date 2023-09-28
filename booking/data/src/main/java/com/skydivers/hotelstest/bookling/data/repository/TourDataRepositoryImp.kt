package com.skydivers.hotelstest.bookling.data.repository

import com.skydivers.hotelstest.booking.domain.repository.TourDataRepository
import com.skydivers.hotelstest.booking.feature.UiState
import com.skydivers.hotelstest.booking.model.BookingModel
import com.skydivers.hotelstest.bookling.data.service.BookingService
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
    override suspend fun fetchTourData(): Flow<UiState> =
        flow {

            emit(fetchBookingDataFromServer())


        }.flowOn(Dispatchers.IO)
            .catch {
                emit(
                    UiState.Error(
                        com.skydivers.hotelstest.booking.model.ErrorUIModel(
                            "DataRepository",
                            message = it.message.orEmpty()
                        )
                    )
                )
            }


    private suspend fun fetchBookingDataFromServer(): UiState {
        return setState(response = service.fetchBookingData())
    }


    private fun setState(
        response: Response<BookingModel>
    ): UiState {
        var uiState: UiState? = null
        try {

            if (response.code() == 200) {
                uiState = UiState.Success(response.body()!!)

            } else {
                uiState = UiState.Error(
                    com.skydivers.hotelstest.booking.model.ErrorUIModel(
                        "Response",
                        response.message() + " ${response.code()}"
                    )
                )
                response.errorBody()!!.close()
            }
        } catch (e: Exception) {
            UiState.Error(
                com.skydivers.hotelstest.booking.model.ErrorUIModel(
                    "Response",
                    e.message.orEmpty()
                )
            )

        }

        return uiState!!
    }


}

