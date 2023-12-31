package com.skydivers.hotelstest.features.booking.domain.usecases.validation


import com.skydivers.hotelstest.features.booking.domain.repository.ValidateEmailRepository
import com.skydivers.hotelstest.booking.model.BookingModel
import com.skydivers.hotelstest.core.common.UiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

class ValidateEmailUseCase(private val validateEmailRepository: ValidateEmailRepository) {
    suspend operator fun invoke(
        email: String,
        state: UiState.Success<BookingModel>
    ): UiState.Success<BookingModel> {

        with(state) {
            runBlocking {
                withContext(Dispatchers.IO) {
                    data.buyerInfo.emailError =
                        validateEmailRepository.validate(email).errorMessage
                }
            }
        }
        return state

    }


}



