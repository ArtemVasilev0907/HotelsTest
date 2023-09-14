package com.skydivers.hotelstest.ui.fragments.hotel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.skydivers.domain.DataState
import com.skydivers.domain.usecases.GetHotelsDataUseCase
import com.skydivers.hotelstest.models.ErrorUIModel
import com.skydivers.hotelstest.models.hotel.mapToPresentation
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class HotelViewModel(
    private val getHotelsDataUseCase: GetHotelsDataUseCase,
) : ViewModel() {

    private var _errorEvents = MutableLiveData<ErrorUIModel>()
    var errorEvent: LiveData<ErrorUIModel> = _errorEvents

    private val _hotelUiState = MutableStateFlow(
        HotelUiState.State(

        )
    )


    val hotelUiState = _hotelUiState.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = HotelUiState.State()
    )

     suspend fun fetchHotelData() {

        getHotelsDataUseCase().flowOn(Dispatchers.IO).stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = DataState.Loading()
        ).onEach { dataState ->
            when (dataState) {
                is DataState.Success -> {
                    _hotelUiState.update {
                        it.copy(
                            isSuccess = true,
                            result = dataState.data.mapToPresentation()

                        )

                    }
                }
                is DataState.Error -> {
                    _errorEvents.value = ErrorUIModel("DataState",dataState.message.orEmpty() )
                    _hotelUiState.update {
                        it.copy(
                           error = ErrorUIModel("DataState",dataState.message.orEmpty() )


                        )

                    }
                }
                is DataState.Loading -> {
                    _hotelUiState.update {
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
        viewModelScope.launch {
            val error = throwable.message.orEmpty()
            Log.e(this::class.simpleName, error)

        }
    }
}