package com.skydivers.hotelstest.booking.model


data class BuyerInfoUIModel(
    val id: Int = 0,
    var phone: String? = null,
    var email: String? = null,
    var emailError:String?=null,
    var phoneError:String?=null,
    var isSuccessful:Boolean = false
)



