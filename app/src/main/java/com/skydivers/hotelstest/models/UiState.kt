package com.skydivers.hotelstest.models


sealed class UiState {
    object Loading : UiState()
    data class Success(val data: BaseUIModel) : UiState()
    data class Error(val errorModel: ErrorUIModel) : UiState()
}