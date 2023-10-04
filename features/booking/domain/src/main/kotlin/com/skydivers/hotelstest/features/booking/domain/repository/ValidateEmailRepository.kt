package com.skydivers.hotelstest.features.booking.domain.repository

import com.skydivers.hotelstest.features.booking.domain.usecases.validation.ValidationResultModel

interface ValidateEmailRepository {

    fun validate(email:String): ValidationResultModel
}