package com.skydivers.hotelstest.bookling.data.repository.validate

import android.util.Patterns
import com.skydivers.hotelstest.booking.domain.repository.ValidateEmailRepository
import com.skydivers.hotelstest.booking.domain.usecases.validation.ValidationResult

class ValidateEmailImpl: ValidateEmailRepository {
    override fun validate(email: String): ValidationResult {
        if (email.isBlank()) {
            return ValidationResult(
                successful = false,
                errorMessage = "Укажите почту !"
            )
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            return ValidationResult(
                successful = false,
                errorMessage = "Не корректная почта!"
            )
        }



        return ValidationResult(
            successful = true
        )
    }
}