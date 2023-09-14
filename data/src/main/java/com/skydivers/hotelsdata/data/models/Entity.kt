package com.skydivers.hotelsdata.data.models

import com.skydivers.domain.models.ModelDomain

interface Entity {
    fun mapToDomain():ModelDomain
}