package com.skydivers.hotelstest.bookling.data.service


import com.skydivers.hotelstest.bookling.data.entities.TourEntity

import retrofit2.Response

import retrofit2.http.GET


internal interface BookingService {

    @GET("e8868481-743f-4eb2-a0d7-2bc4012275c8")
    suspend fun fetchBookingData(

    ): Response<TourEntity>


}