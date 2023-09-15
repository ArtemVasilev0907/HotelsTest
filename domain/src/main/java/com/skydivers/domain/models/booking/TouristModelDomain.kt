package com.skydivers.domain.models.booking

import com.skydivers.domain.models.ModelDomain

data class TouristModelDomain(
    var id : Int ,
    var title: String,
    var firstName : String,
    var lastName: String,
    var birthDay:String,
    var citizenship: String,
    var passportNumber: String,
    var passportEndDate:String

):ModelDomain

