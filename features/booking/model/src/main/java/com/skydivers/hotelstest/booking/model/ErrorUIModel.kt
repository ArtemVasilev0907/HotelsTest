package com.skydivers.hotelstest.booking.model

import android.util.Log


data class ErrorUIModel(val tag: String, var message: String) {

    fun logging() {
        Log.e(tag, message)
    }
}
