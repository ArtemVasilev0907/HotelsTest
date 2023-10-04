package com.skydivers.hotelstest.bookling.data.repository.validate

import com.skydivers.hotelstest.features.booking.domain.repository.ValidatePhoneRepository
import com.skydivers.hotelstest.features.booking.domain.usecases.validation.ValidationResultModel

class ValidatePhoneImpl: ValidatePhoneRepository {
    override fun validate(phone: String): ValidationResultModel {
        if (phone.isBlank()){
            return ValidationResultModel(
                successful = false,
                errorMessage = "Укажите номер телефона !"
            )
        }
        if (phone.length != 18){
            return ValidationResultModel(
                successful = false,
                errorMessage = "Не верно заполнен номер телефона!!"
            )
        }

        return ValidationResultModel(
            successful = true
        )
    }
}