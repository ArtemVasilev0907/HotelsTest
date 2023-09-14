package com.skydivers.domain

sealed interface DataState<T> {
    class Success<T>(val data:T): DataState<T>
    class Error<T>(val message: String?, val code: Int?=0, val data:T?=null) : DataState<T>
    class Loading<T> : DataState<T>
}