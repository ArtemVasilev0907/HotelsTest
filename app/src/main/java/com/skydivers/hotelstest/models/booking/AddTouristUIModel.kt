package com.skydivers.hotelstest.models.booking

import com.skydivers.hotelstest.R
import com.skydivers.hotelstest.models.BaseUIModel

data class AddTouristUIModel(
    val addButton:Int = R.drawable.plus
):BaseUIModel {
    override fun isFilled(): Boolean = true
}
