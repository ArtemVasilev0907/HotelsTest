package com.skydivers.hotelstest.booking.domain.repository

import com.skydivers.hotelstest.booking.domain.usecases.validation.ValidationResult

interface ValidatePhoneRepository {

    fun validate(phone:String):ValidationResult
}