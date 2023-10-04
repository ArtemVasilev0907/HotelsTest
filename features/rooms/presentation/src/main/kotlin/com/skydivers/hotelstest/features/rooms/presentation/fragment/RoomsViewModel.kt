package com.skydivers.hotelstest.features.rooms.presentation.fragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.skydivers.hotelstest.booking.feature.navigation.RoomsNavigator


import com.skydivers.hotelstest.core.common.UiState
import com.skydivers.hotelstest.features.rooms.domain.usecases.GetRoomsUseCase
import com.skydivers.hotelstest.features.rooms.presentation.model.RoomsModel
import com.skydivers.hotelstest.features.rooms.presentation.model.mapToPresentation
import kotlinx.coroutines.Dispatchers


import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class RoomsViewModel(
    private val getRoomsUseCase: GetRoomsUseCase,
    private val roomsNavigator: RoomsNavigator
):ViewModel() {

    private val _uiState = MutableStateFlow<UiState<RoomsModel>>(UiState.Loading)
    val uiState = _uiState.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = UiState.Loading)

    init{

        fetchData()
    }

    fun fetchData(){
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                _uiState.update { UiState.Loading }
                getRoomsUseCase().collect{ roomsModelDomain ->
                    _uiState.emit(roomsModelDomain.suspendMap {
                        it.mapToPresentation()
                    })
                }
            }
        }
    }
    fun toBooking(){
        roomsNavigator.toPayFragment()
    }


}

