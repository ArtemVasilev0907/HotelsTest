package com.skydivers.hotelstest.ui.fragments.rooms

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.skydivers.domain.DataState
import com.skydivers.domain.usecases.GetRoomsDataUseCase
import com.skydivers.hotelstest.models.rooms.mapToPresentation
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class RoomsViewModel(
    private val getRoomsDataUseCase: GetRoomsDataUseCase,
) : ViewModel() {

    private val _roomsUiState = MutableStateFlow(
       RoomUiState.State(

        )
    )


    val roomsUiState = _roomsUiState.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = RoomUiState.State()
    )

     suspend fun fetchData() {

         getRoomsDataUseCase().flowOn(Dispatchers.IO).stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = DataState.Loading()
        ).onEach { dataState ->
            when (dataState) {
                is DataState.Success -> {
                    _roomsUiState.update {
                        it.copy(
                            isSuccess = true,
                            result = dataState.data.mapToPresentation()

                        )

                    }
                }
                is DataState.Error -> {
                    _roomsUiState.update {
                        it.copy(
                            isSuccess = false,

                        )

                    }
                }
                is DataState.Loading -> {
                    _roomsUiState.update {
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