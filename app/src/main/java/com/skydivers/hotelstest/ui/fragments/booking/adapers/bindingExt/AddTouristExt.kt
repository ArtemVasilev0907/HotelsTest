package com.skydivers.hotelstest.ui.fragments.booking.adapers.bindingExt

import androidx.recyclerview.widget.RecyclerView
import com.skydivers.hotelstest.databinding.AddTouristItemBinding
import com.skydivers.hotelstest.models.booking.AddTouristUIModel
import com.skydivers.hotelstest.models.booking.TouristUIModel
import com.skydivers.hotelstest.ui.action.BookingUserAction
import com.skydivers.hotelstest.ui.design.delegateAdaper.CompositeDelegateAdapter
import com.skydivers.hotelstest.ui.fragments.booking.adapers.TouristDelegateAdapter

private var adapter = CompositeDelegateAdapter(TouristDelegateAdapter())


fun AddTouristItemBinding.bindData(

     item: AddTouristUIModel,
     onUserAction: ((BookingUserAction) -> Unit)? = null
) {
    image.setImageResource(item.addButton)
    image.setOnClickListener {
        onUserAction?.invoke(BookingUserAction.AddTourist)



    }
}