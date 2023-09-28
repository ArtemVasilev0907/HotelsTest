package com.skydivers.hotelstest.booking.ui.adapers.bindingExt


import com.skydivers.hotelstest.booking.ui.databinding.AddTouristItemBinding
import com.skydivers.hotelstest.booking.ui.repositories.BookingUserAction


internal fun AddTouristItemBinding.bindData(
    onUserAction: ((BookingUserAction) -> Unit)? = null
) {
    image.setOnClickListener {
        onUserAction?.invoke(BookingUserAction.AddTourist)


    }
}