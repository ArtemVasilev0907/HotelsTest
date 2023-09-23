package com.skydivers.hotelstest.booking


import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.skydivers.hotelstest.booking.action.BookingUserAction
import com.skydivers.hotelstest.booking.model.TouristUIModel
import com.skydivers.hotelstest.booking.navigation.BookingNavigator
import com.skydivers.hotelstest.booking.navigation.BookingNavigator2
import com.skydivers.hotelstest.booking.usecases.GetBookingDataUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch


internal class BookingViewModel(
    val getBookingDataUseCase: GetBookingDataUseCase,
    private val bookingNavigator: BookingNavigator,
    private val bookingNavigator2: BookingNavigator2
) : ViewModel() {



    private val _uiState = MutableStateFlow<UiState>(UiState.Loading)
    val uiState = _uiState.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = UiState.Loading
    )


//    private val _navigateTo = MutableLiveData<Screen>()
//    val navigateTo: LiveData<Screen> = _navigateTo


    suspend fun fetchBookingData() {

        Log.e("BookingViewModel", "fetchBooking")
        getBookingDataUseCase().collect { state ->
            if (state is UiState.Success) {
                state.data.rebuild()
            }
            _uiState.emit(state)
        }


    }


    private suspend fun updateTourists(tourists: List<TouristUIModel>?) {
        for (i in tourists?.indices!!) {
            tourists[i].setNewId(i)
        }

        when (val state = uiState.value) {
            is UiState.Success -> {
                state.data.let { booking ->


                    booking.bookingPriceUIModel.multiple(tourists.size)
                    booking.tourists = tourists.toList()
                    _uiState.emit(UiState.Success(booking))
                    Log.e("_uiState", "update")
                }
            }

            else -> {}


        }

    }

    private suspend fun addTourist() {

        when (val state = uiState.value) {
            is UiState.Success -> {
                state.data.let { booking ->
                    val tourists = booking.tourists.toMutableList()
                    tourists.map {
                        it.isCollapsed = false
                    }
                    tourists.add(TouristUIModel().addNewFromList(tourists))
                    updateTourists(tourists)
                }
            }

            else -> {}
        }

    }

    private suspend fun deleteTourist(index: Int) {

        when (val state = uiState.value) {
            is UiState.Success -> {
                state.data.let { booking ->
                    val tourists = booking.tourists.toMutableList()
                    tourists.indexOfFirst { it.id == index }.let {
                        tourists.removeAt(it)
                    }
                    updateTourists(tourists)
                }
            }

            else -> {}
        }

    }

    fun onUserAction(action: BookingUserAction) {
        viewModelScope.launch {
            when (action) {
                is BookingUserAction.AddTourist -> {
                    addTourist()
                }

                is BookingUserAction.DeleteTourist -> {
                    deleteTourist(action.touristId)
                }

                is BookingUserAction.SaveTourist -> {

                    bookingNavigator2.toPayFragment2()
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