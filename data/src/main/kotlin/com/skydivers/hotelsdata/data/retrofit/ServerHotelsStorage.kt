package com.skydivers.hotelsdata.data.retrofit



import com.skydivers.hotelsdata.data.models.booking.BookingEntity
import com.skydivers.hotelsdata.data.models.hotels.HotelsEntity
import com.skydivers.hotelsdata.data.models.rooms.RoomsEntity
import retrofit2.Response

import retrofit2.http.GET



interface ServerHotelsStorage {



    @GET("35e0d18e-2521-4f1b-a575-f0fe366f66e3")
    suspend fun fetchHotelData(

    ): Response<HotelsEntity>

    @GET("f9a38183-6f95-43aa-853a-9c83cbb05ecd")
    suspend fun fetchRoomsData(

    ): Response<RoomsEntity>

    @GET("e8868481-743f-4eb2-a0d7-2bc4012275c8")
    suspend fun fetchBookingData(

    ): Response<BookingEntity>




}