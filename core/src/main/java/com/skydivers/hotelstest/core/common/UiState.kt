package com.skydivers.hotelstest.core.common

import java.lang.Exception


sealed class UiState<out T> {
    abstract suspend fun <R> suspendMap(mapper: (suspend (T) -> R)? = null): UiState<R>
    data object Loading : UiState<Nothing>() {
        override suspend fun <R> suspendMap(mapper: (suspend (Nothing) -> R)?): UiState<R> {
            return this
        }
    }

    data class Success<T>(val data: T) : UiState<T>(){
         override suspend fun <R> suspendMap(mapper: (suspend (T) -> R)?): UiState<R> {
            if (mapper == null) throw IllegalStateException("Can't map UiState.Success without mapper")
            return try {
                Success(mapper(data))
            } catch (e: Exception) {
                Error(e)
            }
        }
    }
    data class Error(val exception: Exception) : UiState<Nothing>() {
        override suspend fun <R> suspendMap(mapper: (suspend (Nothing) -> R)?): UiState<R> {
            return this
        }
    }
}
suspend fun <T> UiState<T>.observe(
    onLoading: (suspend (UiState.Loading)-> Unit)?= null,
    onSuccess: (suspend (UiState.Success<T>)-> Unit)?= null,
    onError: (suspend (UiState.Error)-> Unit)?= null) {

    if (this is UiState.Error){
        onError?.invoke(this)
    }
    if (this is UiState.Success){
        onSuccess?.invoke(this)
    }
    if (this is UiState.Loading){
        onLoading?.invoke(this)
    }
}


