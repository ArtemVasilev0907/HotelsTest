package com.skydivers.hotelstest.ui.fragments.booking.adapers.bindingExt

import com.skydivers.hotelstest.databinding.BookingBlockItemBinding
import com.skydivers.hotelstest.models.booking.BookingModel

fun BookingBlockItemBinding.bind(item: BookingModel){
    departure.text = item.departure
    arrivalCountry.text = item.arrivalCountry
    tourDate.text = item.tourDateStart + " - " + item.tourDateStop
    nights.text = item.numberOfNights.toString() + " ночей"
    hotel.text = item.hotelName
    room.text = item.room
    nutrition.text = item.nutrition
}