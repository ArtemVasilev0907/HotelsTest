package com.skydivers.hotelstest.booking.domain.repository

import com.skydivers.hotelstest.booking.domain.usecases.validation.ValidationResult

interface ValidateEmptyTextRepository {
    fun validate(value: String):ValidationResult
}