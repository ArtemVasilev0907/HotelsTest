package com.skydivers.hotelstest.ui.fragments.booking


import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.skydivers.domain.DataState
import com.skydivers.domain.usecases.GetBookingDataUseCase
import com.skydivers.hotelstest.models.ErrorUIModel
import com.skydivers.hotelstest.models.booking.TouristUIModel
import com.skydivers.hotelstest.models.booking.mapToPresentation
import com.skydivers.hotelstest.ui.action.BookingUserAction
import com.skydivers.hotelstest.ui.navigation.Screen
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch


class BookingViewModel(
    val getBookingDataUseCase: GetBookingDataUseCase,
    ) : ViewModel() {


    private val _bookingUiState = MutableStateFlow(
        BookingUiState.State()
    )


    val bookingUiState = _bookingUiState.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = BookingUiState.State()
    )

    private var _errorEvents = MutableLiveData<ErrorUIModel>()
    var errorEvent: LiveData<ErrorUIModel> = _errorEvents

    private val _navigateTo = MutableLiveData<Screen>()
    val navigateTo: LiveData<Screen> = _navigateTo


    suspend fun fetchBookingData() {

        getBookingDataUseCase().flowOn(Dispatchers.IO).stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = DataState.Loading()
        ).onEach { dataState ->
            when (dataState) {
                is DataState.Success -> {
                    _bookingUiState.update {
                        it.copy(
                            isSuccess = true,
                            result = dataState.data.mapToPresentation()
                        )
                    }
                }
                is DataState.Error -> {
                    Log.e("DataState", "Error loading")
                    _errorEvents.value = ErrorUIModel("DataState", dataState.message.orEmpty())
                    _bookingUiState.update {
                        it.copy(
                            isSuccess = false,
                            )
                    }
                }
                is DataState.Loading -> {
                    _bookingUiState.update {
                        it.copy(
                            isSuccess = false,
                        )
                    }
                }

            }

        }.catch {
            onPrepareError(it)
        }.launchIn(viewModelScope)
    }


    private fun onPrepareError(throwable: Throwable) {

        val error = throwable.message.orEmpty()
        Log.e(this::class.simpleName, error)
        _errorEvents.value = this::class.simpleName?.let { ErrorUIModel(it, error) }

    }


    fun onUserAction(action: BookingUserAction) {
        viewModelScope.launch {
            when (action) {
                is BookingUserAction.AddTourist -> {

                    val tourists = bookingUiState.value.result?.tourists?.toMutableList()
                    tourists?.add(TouristUIModel().setIdFromList(tourists.toMutableList()))
                    val dataState = bookingUiState.value
                    dataState.result?.bookingPriceUIModel?.multiple(tourists?.size!!)
                    if (tourists != null) {
                        dataState.result?.tourists = tourists.toList()
                    }
                    _bookingUiState.emit(dataState)
                }
                is BookingUserAction.DeleteTourist -> {

                }
                is BookingUserAction.SaveTourist -> {

                }
                is BookingUserAction.CheckPay -> {

                }
                is BookingUserAction.BuyTour -> {
                    _navigateTo.value = Screen.Paid
                }
            }
        }

    }


}