package com.skydivers.hotelstest.features.booking.domain.usecases.validation

data class ValidationResultModel(
    val successful: Boolean,
    val errorMessage:String?=null
)
