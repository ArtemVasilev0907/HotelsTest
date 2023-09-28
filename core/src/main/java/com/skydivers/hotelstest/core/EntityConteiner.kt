package com.skydivers.hotelstest.core

import com.skydivers.hotelstest.core.entities.EntityCore
import com.skydivers.hotelstest.core.entities.ErrorEntity


sealed class EntityContainer {
    data object Loading : EntityContainer()
    data class Success(val data: EntityCore) : EntityContainer()
    data class Error(val errorModel: ErrorEntity) : EntityContainer()
}
