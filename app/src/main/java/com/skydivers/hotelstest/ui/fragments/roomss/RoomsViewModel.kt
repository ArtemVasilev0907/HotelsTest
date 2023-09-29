package com.skydivers.hotelstest.ui.fragments.roomss

import androidx.lifecycle.viewModelScope
import com.skydivers.domain.usecases.GetRoomsDataUseCase
import com.skydivers.hotelstest.BaseViewModel
import com.skydivers.hotelstest.models.ErrorUIModel
import com.skydivers.hotelstest.models.UiState
import com.skydivers.hotelstest.models.rooms.mapToPresentation
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

class RoomsViewModel(
    private val getRoomsDataUseCase: GetRoomsDataUseCase,
) : BaseViewModel() {

    private val _uiState = MutableStateFlow<UiState>(UiState.Loading)
    val uiState = _uiState.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = UiState.Loading)

    suspend fun fetchData() {
        getRoomsDataUseCase().fetch(
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

