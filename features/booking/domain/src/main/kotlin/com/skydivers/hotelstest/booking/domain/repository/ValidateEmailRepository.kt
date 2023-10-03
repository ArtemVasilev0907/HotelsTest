package com.skydivers.hotelstest.booking.domain.repository

import com.skydivers.hotelstest.booking.domain.usecases.validation.ValidationResult

interface ValidateEmailRepository {

    fun validate(email:String):ValidationResult
}