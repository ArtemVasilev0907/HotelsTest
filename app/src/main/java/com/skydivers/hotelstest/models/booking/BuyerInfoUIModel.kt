package com.skydivers.hotelstest.models.booking

import com.skydivers.hotelstest.models.BaseUIModel

data class BuyerInfoUIModel(
    val id:Int = 0,
    var phone:String?=null,
    var email:String?=null
):BaseUIModel{
    override fun isFilled() :Boolean  {

        return listOf(
            this.phone,
            this.email).all{
            it!=null
        }
    }
}



