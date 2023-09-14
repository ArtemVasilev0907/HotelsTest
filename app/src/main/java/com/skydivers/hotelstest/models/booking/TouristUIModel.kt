package com.skydivers.hotelstest.models.booking

import com.skydivers.hotelstest.models.BaseUIModel

data class TouristUIModel(
    var id : Int = 1,
    var title: String? =null,
    var firstName : String? =null,
    var lastName: String? =null,
    var birthDay:String? =null,
    var citizenship: String? =null,
    var passportNumber: String? =null,
    var passportEndDate:String? =null,
    var isCollapsed:Boolean = true

): BaseUIModel{
    fun  setIdFromList(modelList: ArrayList<TouristUIModel>): TouristUIModel{
        var newId = 1
        for (i in 0 until modelList.size){


                newId++

        }

        val title =  digitsToText(newId)
        return TouristUIModel(id = newId, title = title)
    }
    override fun isFilled() :Boolean  {

        return listOf(this.firstName,
            this.lastName,
            this.birthDay,
            this.citizenship,
            this.passportNumber,
            this.passportEndDate).all{
            it!=null
        }
    }

}

