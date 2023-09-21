package com.skydivers.hotelstest.ui.fragments.hotel

import androidx.lifecycle.viewModelScope
import com.skydivers.domain.usecases.GetHotelsDataUseCase
import com.skydivers.hotelstest.BaseViewModel
import com.skydivers.hotelstest.models.ErrorUIModel
import com.skydivers.hotelstest.models.UiState
import com.skydivers.hotelstest.models.hotel.mapToPresentation
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch


class HotelViewModel(
    private val getHotelsDataUseCase: GetHotelsDataUseCase,
) : BaseViewModel() {

    private val _uiState = MutableStateFlow<UiState>(UiState.Loading)
    val uiState = _uiState.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = UiState.Loading
    )

     suspend fun fetchHotelData() {
         viewModelScope.launch {
             getHotelsDataUseCase().fetch(
                 onSuccess = {success->
                     _uiState.update {
                         UiState.Success(success.mapToPresentation())
                     }
                 },
                 onLoading = {

                 },
                 onError = {error->
                     UiState.Error(ErrorUIModel(error.code.toString(), error.message.orEmpty()) )
                 }
             )
         }

    }


}




