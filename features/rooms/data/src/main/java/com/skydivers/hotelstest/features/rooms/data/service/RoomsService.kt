package com.skydivers.hotelstest.features.rooms.data.service



import com.skydivers.hotelstest.features.rooms.data.entities.RoomsEntity

import retrofit2.Response

import retrofit2.http.GET


internal interface RoomsService {

    @GET("f9a38183-6f95-43aa-853a-9c83cbb05ecd")
    suspend fun getData(

    ): Response<RoomsEntity>


}