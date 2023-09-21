package com.skydivers.hotelstest

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.skydivers.domain.DataState
import com.skydivers.domain.DataState.*
import com.skydivers.hotelstest.models.ErrorUIModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

open class BaseViewModel() : ViewModel() {

    private var _errorEvents = MutableLiveData<ErrorUIModel>()
    var errorEvent: LiveData<ErrorUIModel> = _errorEvents

    private var _onLoading = MutableLiveData<Boolean>(true)
    val isLoading: LiveData<Boolean> = _onLoading

    private fun onPrepareError(throwable: Throwable) {
        viewModelScope.launch {
            val error = throwable.message.orEmpty()
            Log.e(this::class.simpleName, error)
        }
    }


    suspend fun <T> Flow<DataState<T>>.fetch(
        onSuccess: ((T) -> Unit)? = null,
        onLoading: ((Loading<T>) -> Unit)? = null,
        onError: ((Error<T>) -> Unit)? = null
    ) {
        flowOn(Dispatchers.IO).stateIn(
            scope = viewModelScope
        ).onEach { dataState ->
            when (dataState) {
                is Success -> {
                    onSuccess?.invoke(dataState.data)
                    _onLoading.value = false
                }
                is Error -> {
                    _errorEvents.value = ErrorUIModel("DataState", dataState.message.orEmpty())
                    onError?.invoke(dataState)
                    _onLoading.value = false

                }
                is Loading -> {
                    onLoading?.invoke(dataState)
                    _onLoading.value = true

                }
            }
        }.catch {
            onPrepareError(it)
        }.launchIn(viewModelScope)
    }
}


