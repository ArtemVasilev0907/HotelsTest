package com.skydivers.hotelstest.features.booking.ui


import androidx.lifecycle.viewModelScope
import com.skydivers.hotelstest.booking.model.BookingModel
import com.skydivers.hotelstest.core.common.UiState
import com.skydivers.hotelstest.core.common.observe
import com.skydivers.hotelstest.core.ui.lyfecycles.BaseViewModel
import com.skydivers.hotelstest.features.booking.domain.usecases.AddTouristUseCase
import com.skydivers.hotelstest.features.booking.domain.usecases.DeleteTouristUseCase
import com.skydivers.hotelstest.features.booking.domain.usecases.GetBookingDataUseCase
import com.skydivers.hotelstest.features.booking.domain.usecases.validation.ValidateEmailUseCase
import com.skydivers.hotelstest.features.booking.domain.usecases.validation.ValidatePhoneUseCase
import com.skydivers.hotelstest.features.booking.ui.navigation.BookingNavigator
import com.skydivers.hotelstest.features.booking.ui.repositories.BookingUserAction
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


internal class BookingViewModel(
    val getBookingDataUseCase: GetBookingDataUseCase,
    val addTouristUseCase: AddTouristUseCase,
    val deleteTouristUseCase: DeleteTouristUseCase,
    val validateEmailUseCase: ValidateEmailUseCase,
    val validatePhoneUseCase: ValidatePhoneUseCase,
    private val bookingNavigator: BookingNavigator
) : BaseViewModel() {


    private val _uiState = MutableStateFlow<UiState<BookingModel>>(UiState.Loading)
    val uiState = _uiState.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = UiState.Loading
    )

    init {
        fetchBookingData()
    }


    fun fetchBookingData() {
        launchInIO {
            _uiState.update { UiState.Loading }
            getBookingDataUseCase().collectLatest { state ->
                _uiState.emit(state)
            }
        }

    }


    fun onUserAction(action: BookingUserAction) {
        viewModelScope.launch {

            when (action) {
                is BookingUserAction.AddTourist -> {
                    uiState.value.observe(
                        onSuccess = { state ->
                            _uiState.update {
                                addTouristUseCase(state)
                            }
                        }
                    )
                }

                is BookingUserAction.DeleteTourist -> {
                    uiState.value.observe(
                        onSuccess = { state ->
                            _uiState.update {
                                deleteTouristUseCase(state, action.touristId)
                            }
                        }
                    )
                }

                is BookingUserAction.SaveTourist -> {}

                is BookingUserAction.CheckPay -> {}

                is BookingUserAction.BuyTour -> {
                    bookingNavigator.toPayFragment()
                }

                is BookingUserAction.ValidateEmail -> {
                    uiState.value.observe(
                        onSuccess = { state ->
                            _uiState.update {
                                validateEmailUseCase(action.email, state)
                            }
                        }
                    )
                }

                is BookingUserAction.ValidatePhone -> {
                    uiState.value.observe(
                        onSuccess = { state ->
                            _uiState.update {
                                validatePhoneUseCase(action.phone, state)
                            }
                        }
                    )
                }
            }
        }

    }


}

