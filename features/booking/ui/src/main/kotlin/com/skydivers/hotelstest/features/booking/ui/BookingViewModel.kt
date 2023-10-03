package com.skydivers.hotelstest.features.booking.ui


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.skydivers.hotelstest.booking.domain.usecases.AddTouristUseCase
import com.skydivers.hotelstest.booking.domain.usecases.DeleteTouristUseCase
import com.skydivers.hotelstest.booking.domain.usecases.GetBookingDataUseCase
import com.skydivers.hotelstest.booking.domain.usecases.validation.ValidateEmailUseCase
import com.skydivers.hotelstest.booking.domain.usecases.validation.ValidatePhoneUseCase
import com.skydivers.hotelstest.booking.model.BookingModel
import com.skydivers.hotelstest.core.common.UiState
import com.skydivers.hotelstest.core.common.observe
import com.skydivers.hotelstest.features.booking.ui.navigation.BookingNavigator
import com.skydivers.hotelstest.features.booking.ui.repositories.BookingUserAction
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


internal class BookingViewModel(
    val getBookingDataUseCase: GetBookingDataUseCase,
    val addTouristUseCase: AddTouristUseCase,
    val deleteTouristUseCase: DeleteTouristUseCase,
    val validateEmailUseCase: ValidateEmailUseCase,
    val validatePhoneUseCase: ValidatePhoneUseCase,
    private val bookingNavigator: BookingNavigator
) : ViewModel() {


    private val _uiState = MutableStateFlow<UiState<BookingModel>>(UiState.Loading)
    val uiState = _uiState.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = UiState.Loading
    )

    init{
        fetchBookingData()
    }


    fun fetchBookingData() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                getBookingDataUseCase().collectLatest { state ->
                    _uiState.emit(state)
                }
            }
        }
    }


    fun onUserAction(action: BookingUserAction) {
        viewModelScope.launch {
            uiState.value.observe()
            when (action) {
                is BookingUserAction.AddTourist -> {
                    uiState.value.observe(
                        onSuccess = {
                            _uiState.emit(addTouristUseCase(it))
                        }
                    )
                }

                is BookingUserAction.DeleteTourist -> {
                    uiState.value.observe(
                        onSuccess = {
                            _uiState.emit(deleteTouristUseCase(it, action.touristId))
                        }
                    )
                }

                is BookingUserAction.SaveTourist -> {

                }

                is BookingUserAction.CheckPay -> {

                }

                is BookingUserAction.BuyTour -> {
                    bookingNavigator.toPayFragment()
                }

                is BookingUserAction.ValidateEmail -> {
                    uiState.value.observe(
                        onSuccess = {
                            _uiState.emit(validateEmailUseCase(action.email, it))
                        }
                    )
                }

                is BookingUserAction.ValidatePhone -> {
                    uiState.value.observe(
                        onSuccess = {
                            _uiState.emit(validatePhoneUseCase(action.phone, it))
                        }
                    )
                }


            }


        }

    }


}

