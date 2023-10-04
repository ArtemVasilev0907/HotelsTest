package com.skydivers.hotelstest.features.booking.domain.repository

import com.skydivers.hotelstest.features.booking.domain.usecases.validation.ValidationResultModel

interface ValidateEmptyTextRepository {
    fun validate(value: String): ValidationResultModel
}