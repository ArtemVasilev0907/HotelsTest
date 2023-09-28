package com.skydivers.hotelsdata.data.models.booking

import com.google.gson.annotations.SerializedName
import com.skydivers.domain.models.booking.TouristModelDomain
import com.skydivers.hotelsdata.data.models.Entity

data class TouristEntity(
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
    var passportEndDate: String = ""

) : Entity {

    override fun mapToDomain(): TouristModelDomain {
        return TouristModelDomain(
            id,
            title,
            firstName,
            lastName,
            birthDay,
            citizenship,
            passportNumber,
            passportEndDate
        )
    }

}

