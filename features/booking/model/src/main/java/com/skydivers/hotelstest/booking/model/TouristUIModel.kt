package com.skydivers.hotelstest.booking.model

import com.google.gson.annotations.SerializedName


data class TouristUIModel(

    @SerializedName("id")
    var id: Int = 1,
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
    fun addNewFromList(tourists: MutableList<TouristUIModel>): TouristUIModel {

        val newId = tourists.size +1
        val title = digitsToText(newId)
        return TouristUIModel(id = newId, title = title)
    }

    fun setNewId(index: Int) {
        var newId = index
        newId++
        id = newId
        this.title = digitsToText(newId)
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



