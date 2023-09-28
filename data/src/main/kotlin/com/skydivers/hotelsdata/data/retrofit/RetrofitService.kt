package com.skydivers.hotelsdata.data.retrofit



import com.google.gson.Gson
import com.skydivers.hotelsdata.data.models.booking.BookingEntity
import com.skydivers.hotelsdata.data.models.hotels.HotelsEntity
import com.skydivers.hotelsdata.data.models.rooms.RoomsEntity
import com.skydivers.hotelstest.core.entities.EntityCore
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response

import retrofit2.http.GET
import retrofit2.http.Path
import kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinClassFinder


interface RetrofitService {



    @GET("{path}")
    suspend fun get( @Path("path")path:String ): Result<String>

    @GET("f9a38183-6f95-43aa-853a-9c83cbb05ecd")
    suspend fun fetchRoomsData(

    ): Response<RoomsEntity>

    @GET("e8868481-743f-4eb2-a0d7-2bc4012275c8")
    suspend fun fetchBookingData(

    ): Response<BookingEntity>




}