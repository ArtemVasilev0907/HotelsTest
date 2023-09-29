package com.skydivers.hotelstest.features.booking.ui


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.skydivers.hotelstest.features.booking.ui.repositories.BookingUserAction
import com.skydivers.hotelstest.booking.domain.usecases.AddTouristUseCase
import com.skydivers.hotelstest.booking.domain.usecases.DeleteTouristUseCase
import com.skydivers.hotelstest.booking.domain.usecases.GetBookingDataUseCase
import com.skydivers.hotelstest.features.booking.ui.navigation.BookingNavigator
import com.skydivers.hotelstest.booking.model.BookingModel
import com.skydivers.hotelstest.core.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch


internal class BookingViewModel(
    val getBookingDataUseCase: GetBookingDataUseCase,
    val addTouristUseCase: AddTouristUseCase,
    val deleteTouristUseCase: DeleteTouristUseCase,
    private val bookingNavigator: BookingNavigator
) : ViewModel() {


    private val _uiState = MutableStateFlow<UiState<BookingModel>>(UiState.Loading)
    val uiState = _uiState.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = UiState.Loading
    )


    fun fetchBookingData() {
        viewModelScope.launch {
            getBookingDataUseCase().collectLatest { state ->
                _uiState.emit(state)
            }
        }
    }


    fun onUserAction(action: BookingUserAction) {
        viewModelScope.launch {



            when (action) {
                is BookingUserAction.AddTourist -> {
                    when (val state = uiState.value) {
                        is UiState.Success -> {
                            _uiState.emit(addTouristUseCase(state))
                        }

                        else -> {}
                    }
                }

                is BookingUserAction.DeleteTourist -> {

                            when (val state = uiState.value) {
                                is UiState.Success -> {
                                    _uiState.emit(deleteTouristUseCase(state, action.touristId))
                                }

                                else -> {}
                            }



                }

                is BookingUserAction.SaveTourist -> {

                }

                is BookingUserAction.CheckPay -> {

                }

                is BookingUserAction.BuyTour -> {
                    bookingNavigator.toPayFragment()
                }


            }


        }

    }
}