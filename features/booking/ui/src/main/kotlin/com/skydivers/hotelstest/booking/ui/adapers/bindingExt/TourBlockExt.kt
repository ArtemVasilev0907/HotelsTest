package com.skydivers.hotelstest.booking.ui.adapers.bindingExt

import com.skydivers.hotelstest.features.booking.ui.R
import com.skydivers.hotelstest.booking.model.BookingModel
import com.skydivers.hotelstest.features.booking.ui.databinding.TourBlockItemBinding


internal fun TourBlockItemBinding.bindData(item: BookingModel) {
    departure.text = item.departure
    arrivalCountry.text = item.arrivalCountry
    tourDate.text =
        root.context.getString(R.string.tour_date_value, item.tourDateStart, item.tourDateStop)
    nights.text = root.context.getString(R.string.tour_nights_number, item.numberOfNights)
    hotel.text = item.hotelName
    room.text = item.room
    nutrition.text = item.nutrition
}