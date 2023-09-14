package com.skydivers.hotelstest.ui.fragments.booking.adapers.bindingExt

import com.skydivers.hotelstest.R
import com.skydivers.hotelstest.databinding.BookingPriceItemBinding
import com.skydivers.hotelstest.models.booking.BookingPriceUIModel

fun BookingPriceItemBinding.calculateData(
    item: BookingPriceUIModel,
    numberOfTourists: Int = 1
) {
    var nunber = numberOfTourists
    if (nunber == 0) nunber = 1
    tourPrice.text = (item.tourPrice * nunber).toCurrencyString("₽")
    fuelCharge.text = (item.fuelCharge * nunber).toCurrencyString("₽")
    serviceCharge.text = (item.serviceCharge * nunber).toCurrencyString("₽")
    toPay.text = (item.toPay() * nunber).toCurrencyString("₽")
    payButton.RoundedButtonView.setText(R.string.pay_btn_title)
}

fun BookingPriceItemBinding.bindData(
    item: BookingPriceUIModel,
    numberOfTourists: Int = 1,
    onUserAction: () -> Unit
) {
    calculateData(item, numberOfTourists)


    payButton.RoundedButtonView.setOnClickListener {

        onUserAction.invoke()


    }

}