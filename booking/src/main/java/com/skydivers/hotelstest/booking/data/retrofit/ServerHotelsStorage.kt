package com.skydivers.hotelstest.booking.data.retrofit


import com.skydivers.hotelstest.booking.model.BookingModel

import retrofit2.Response

import retrofit2.http.GET


internal interface ServerHotelsStorage {

    @GET("e8868481-743f-4eb2-a0d7-2bc4012275c8")
    suspend fun fetchBookingData(

    ): Response<BookingModel>


}