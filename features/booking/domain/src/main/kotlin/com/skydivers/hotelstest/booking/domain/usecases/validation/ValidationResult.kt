package com.skydivers.hotelstest.booking.domain.usecases.validation

data class ValidationResult(
    val successful: Boolean,
    val errorMessage:String?=null
)
