package com.skydivers.hotelstest.features.booking.domain.usecases.validation

import com.skydivers.hotelstest.features.booking.domain.repository.ValidatePhoneRepository
import com.skydivers.hotelstest.booking.model.BookingModel
import com.skydivers.hotelstest.core.common.UiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext


class ValidatePhoneUseCase(private val validatePhoneRepository: ValidatePhoneRepository) {
    suspend operator fun invoke(
        phone: String,
        state: UiState.Success<BookingModel>
    ): UiState.Success<BookingModel> {

        with(state) {
            runBlocking {
                withContext(Dispatchers.IO) {
                    data.buyerInfo.phoneError = validatePhoneRepository.validate(phone).errorMessage
                }
            }
        }
        return state

    }


}



