package com.skydivers.hotelstest.booking.model


data object BuyerInfoUIModel{

    var tourId: Int = 0
    var phone: String? = null
    var email: String? = null
    var emailError:String?=null
    var phoneError:String?=null
    var isSuccessful:Boolean = false
    fun setTour(id:Int){
        tourId = id
    }
}



