package com.skydivers.hotelstest.bookling.data.repository.validate

import com.skydivers.hotelstest.booking.domain.repository.ValidatePhoneRepository
import com.skydivers.hotelstest.booking.domain.usecases.validation.ValidationResult

class ValidatePhoneImpl: ValidatePhoneRepository {
    override fun validate(phone: String): ValidationResult {
        if (phone.isBlank()){
            return ValidationResult(
                successful = false,
                errorMessage = "Укажите номер телефона !"
            )
        }
        if (phone.length != 18){
            return ValidationResult(
                successful = false,
                errorMessage = "Не верно заполнен номер телефона!!"
            )
        }

        return ValidationResult(
            successful = true
        )
    }
}