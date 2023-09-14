package com.skydivers.domain.models.hotels

import com.skydivers.domain.models.ModelDomain


data class AboutTheHotelModelDomain(

    val description: String,

    val peculiarities: List<String>
): ModelDomain