package com.skydivers.hotelstest.booking.model

import com.google.gson.annotations.SerializedName


data class TouristUIModel(

    @SerializedName("id")
    var id: Int = 1,
    var hotelId: Int = 0,
    @SerializedName("title")
    var title: String = "",
    @SerializedName("first_name")
    var firstName: String = "",
    @SerializedName("last_name")
    var lastName: String = "",
    @SerializedName("birthday")
    var birthDay: String = "",
    @SerializedName("citizenship")
    var citizenship: String = "",
    @SerializedName("passport_number")
    var passportNumber: String = "",
    @SerializedName("passport_end_date")
    var passportEndDate: String = "",
    var isCollapsed: Boolean = true

) {
    private fun digitsToText(digit: Int): String = convertToText(digit.toLong())
    companion object{
        fun TouristUIModel.setNewId(index: Int) {
            var newId = index
            newId++
            id = newId
            this.title = convertToText(newId.toLong())
        }
        fun TouristUIModel.addNewFromList(tourists: MutableList<TouristUIModel>): TouristUIModel {

            val newId = tourists.size +1
            val title = convertToText(newId.toLong())
            return TouristUIModel(id = newId, title = title)
        }
    }



    fun isFilled(): Boolean {

        return listOf(
            this.firstName,
            this.lastName,
            this.birthDay,
            this.citizenship,
            this.passportNumber,
            this.passportEndDate
        ).all {
            it != null
        }
    }


}





