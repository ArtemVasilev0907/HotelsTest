package com.skydivers.hotelsdata.data.repository


import com.skydivers.hotelsdata.data.retrofit.RetrofitInstance
import com.skydivers.domain.DataState
import com.skydivers.domain.HotelsDataRepository
import com.skydivers.domain.models.hotels.HotelsModelDomain
import com.skydivers.domain.models.rooms.RoomsModelDomain
import com.skydivers.hotelsdata.data.models.hotels.HotelsEntity
import com.skydivers.hotelsdata.data.models.rooms.RoomsEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.withTimeout
import retrofit2.Response
import java.net.SocketTimeoutException


class HotelsDataRepositoryImp :
    HotelsDataRepository {


    override suspend fun fetchHotelDataState(
    ): Flow<DataState<HotelsModelDomain>> =
        flow {
            emit(fetchHotelDataFromServer())
        }.flowOn(Dispatchers.IO)
            .catch {


                if (it is SocketTimeoutException) {
                    emit(DataState.Error(code = 0, message = "Time out"))
                } else
                    emit(DataState.Error(code = 0, message = it.message.orEmpty()))
            }

    override suspend fun fetchRoomsDataState(): Flow<DataState<RoomsModelDomain>> =
        flow {
            emit(fetchRoomsDataFromServer())
        }.flowOn(Dispatchers.IO)
            .catch {
                emit(DataState.Error(code = 0, message = it.message.orEmpty()))
            }




    private suspend fun fetchHotelDataFromServer(): DataState<HotelsModelDomain> {


//        val result = withTimeout(60000){
//            RetrofitInstance.api.fetchHotelData()
//        }

        return mapHotelDataToDomain(response = RetrofitInstance.api.fetchHotelData())
    }

    private suspend fun fetchRoomsDataFromServer(): DataState<RoomsModelDomain> {


        val result = withTimeout(60000) {
            RetrofitInstance.api.fetchRoomsData()
        }

        return mapRoomsDataToDomain(response = result)
    }




    private suspend fun mapHotelDataToDomain(
        response: Response<HotelsEntity>
    ): DataState<HotelsModelDomain> {
        val dataState = MutableStateFlow<DataState<HotelsModelDomain>>(DataState.Loading())
        try {

            if (response.code() == 200) {
                dataState.emit(DataState.Success(response.body()!!.mapToDomain()))

            } else {

                dataState.emit(
                    DataState.Error(
                        code = response.code(),
                        message = response.message()
                    )
                )
                response.errorBody()!!.close()
            }
        } catch (e: Exception) {
            if (e is SocketTimeoutException) {
                dataState.emit(DataState.Error(code = 0, message = "Time out"))
            } else
                dataState.emit(DataState.Error(message = e.message))
        }

        return dataState.value
    }

    private suspend fun mapRoomsDataToDomain(
        response: Response<RoomsEntity>
    ): DataState<RoomsModelDomain> {
        val dataState = MutableStateFlow<DataState<RoomsModelDomain>>(DataState.Loading())
        try {

            if (response.code() == 200) {
                dataState.emit(DataState.Success(response.body()!!.mapToDomain()))

            } else {
                dataState.emit(
                    DataState.Error(
                        code = response.code(),
                        message = response.message()
                    )
                )
                response.errorBody()!!.close()
            }
        } catch (e: Exception) {
            dataState.emit(DataState.Error(message = e.message))
        }

        return dataState.value
    }




}

