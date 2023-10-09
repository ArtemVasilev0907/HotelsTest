package com.skydivers.hotelstest.core.network.retrofit

import com.skydivers.hotelstest.core.common.UiState
import retrofit2.Response
import java.io.IOException
import java.net.SocketException
import java.net.SocketTimeoutException


infix fun <T>UiState<T>.toState(responce:Response<T>):UiState<T> =

try {

    if (responce.code() == 200) {
        UiState.Success(responce.body()!!)
    } else {
        if (responce.code() > 400) {
            throw SocketException("${responce.code()}: ${responce.message()}")
        }
        if (responce.code() > 500) {
            throw SocketTimeoutException("${responce.code()}: ${responce.message()}")
        }
        throw IOException("${responce.code()}: ${responce.message()}")
    }
} catch (e: Exception) {
    UiState.Error(e)
}
fun <T>Response<T>.toUIState():UiState<T> =

try {

    if (code() == 200) {
        UiState.Success(body()!!)
    } else {
        if (code() > 400) {
            throw SocketException("${code()}: ${message()}")
        }
        if (code() > 500) {
            throw SocketTimeoutException("${code()}: ${message()}")
        }
        throw IOException("${code()}: ${message()}")
    }
} catch (e: Exception) {
    UiState.Error(e)
}