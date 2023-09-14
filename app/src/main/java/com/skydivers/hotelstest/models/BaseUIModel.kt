package com.skydivers.hotelstest.models

import com.skydivers.hotelstest.models.booking.convertToText


interface BaseUIModel {
    fun digitsToText(digit: Int): String = convertToText(digit.toLong())
    fun isFilled() :Boolean


}
