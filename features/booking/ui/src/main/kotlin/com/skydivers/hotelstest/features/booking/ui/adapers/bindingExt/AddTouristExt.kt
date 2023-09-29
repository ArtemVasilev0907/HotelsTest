package com.skydivers.hotelstest.features.booking.ui.adapers.bindingExt



import com.skydivers.hotelstest.features.booking.ui.repositories.BookingUserAction
import com.skydivers.hotelstest.features.booking.ui.databinding.AddTouristItemBinding


internal fun AddTouristItemBinding.bindData(
    onUserAction: ((BookingUserAction) -> Unit)? = null
) {
    image.setOnClickListener {
        onUserAction?.invoke(BookingUserAction.AddTourist)


    }
}