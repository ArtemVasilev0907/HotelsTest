package com.skydivers.hotelstest.booking.data.repository

import android.util.Log
import com.skydivers.hotelstest.booking.UiState
import com.skydivers.hotelstest.booking.data.retrofit.RetrofitInstance
import com.skydivers.hotelstest.booking.model.BookingModel
import com.skydivers.hotelstest.booking.model.ErrorUIModel
import com.skydivers.hotelstest.booking.usecases.BookingDataRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response


internal class BookingDataRepositoryImp :
    BookingDataRepository {


    override suspend fun fetchBookingDataState(): Flow<UiState> =
        flow {
            emit(fetchBookingDataFromServer())
        }.flowOn(Dispatchers.IO)
            .catch {
                emit(UiState.Error(ErrorUIModel("DataRepository", message = it.message.orEmpty())))
            }

    override suspend fun fetchDataState(): Flow<UiState> {
        return flow { emit(UiState.Loading) }
    }


    private suspend fun fetchBookingDataFromServer(): UiState {
        Log.e("retrofit", "getResponse")
        return setState(response = RetrofitInstance.api.fetchBookingData())
    }


    private fun setState(
        response: Response<BookingModel>
    ): UiState {
        Log.e("UiState", "setState")
        var uiState: UiState? = null
        try {


            if (response.code() == 200) {
                uiState = UiState.Success(response.body()!!)
                Log.e("uiState", "success ${response.body()!!}")

            } else {
                uiState = UiState.Error(ErrorUIModel("Response", response.errorBody()!!.string()))
                Log.e("uiState", "error ${response.errorBody()!!}")
                response.errorBody()!!.close()
            }
        } catch (e: Exception) {
            UiState.Error(ErrorUIModel("Response", e.message.orEmpty()))
            Log.e("uiState", "error  ${e.message.orEmpty()}")
        }

        return uiState!!
    }


}

