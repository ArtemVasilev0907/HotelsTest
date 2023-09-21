package com.skydivers.domain




import com.skydivers.domain.models.booking.BookingModelDomain
import com.skydivers.domain.models.hotels.HotelsModelDomain
import com.skydivers.domain.models.rooms.RoomsModelDomain
import kotlinx.coroutines.flow.Flow

interface HotelsDataRepository {


    suspend fun fetchHotelDataState(): Flow<DataState<HotelsModelDomain>>
    suspend fun fetchRoomsDataState(): Flow<DataState<RoomsModelDomain>>



}