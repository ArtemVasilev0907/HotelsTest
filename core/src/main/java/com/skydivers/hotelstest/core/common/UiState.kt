package com.skydivers.hotelstest.core.common

import java.lang.Exception


sealed class UiState<out T> {
    abstract suspend fun <R> suspendMap(mapper: (suspend (T) -> R)? = null): UiState<R>
    object Loading : UiState<Nothing>() {
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
