package com.skydivers.hotelstest.booking.adapers.bindingExt


import com.skydivers.hotelstest.booking.databinding.AddTouristItemBinding
import com.skydivers.hotelstest.booking.action.BookingUserAction
import com.skydivers.hotelstest.booking.model.AddTouristUIModel


internal fun AddTouristItemBinding.bindData(

    item: AddTouristUIModel,
    onUserAction: ((BookingUserAction) -> Unit)? = null
) {
    image.setImageResource(item.addButton)
    image.setOnClickListener {
        onUserAction?.invoke(BookingUserAction.AddTourist)


    }
}