package com.skydivers.data.retrofit



import com.skydivers.data.models.hotels.HotelsEntity
import retrofit2.Response

import retrofit2.http.GET



interface ServerUserStorage {



    @GET("35e0d18e-2521-4f1b-a575-f0fe366f66e3")
    suspend fun fetchHotelData(

    ): Response<HotelsEntity>




}