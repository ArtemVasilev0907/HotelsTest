package com.skydivers.hotelstest.bookling.data.repository.validate

import android.util.Patterns
import com.skydivers.hotelstest.features.booking.domain.repository.ValidateEmailRepository
import com.skydivers.hotelstest.features.booking.domain.usecases.validation.ValidationResultModel

class ValidateEmailImpl: ValidateEmailRepository {
    override fun validate(email: String): ValidationResultModel {
        if (email.isBlank()) {
            return ValidationResultModel(
                successful = false,
                errorMessage = "Укажите почту !"
            )
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            return ValidationResultModel(
                successful = false,
                errorMessage = "Не корректная почта!"
            )
        }



        return ValidationResultModel(
            successful = true
        )
    }
}