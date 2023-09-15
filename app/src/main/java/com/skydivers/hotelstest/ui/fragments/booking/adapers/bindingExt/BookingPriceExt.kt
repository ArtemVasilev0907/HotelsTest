package com.skydivers.hotelstest.ui.fragments.booking.adapers.bindingExt

import com.skydivers.hotelstest.R
import com.skydivers.hotelstest.databinding.BookingPriceItemBinding
import com.skydivers.hotelstest.models.booking.BookingPriceUIModel

fun BookingPriceItemBinding.calculateData(
    item: BookingPriceUIModel
) {

    tourPrice.text = item.tourPrice .toCurrencyString("₽")
    fuelCharge.text = item.fuelCharge .toCurrencyString("₽")
    serviceCharge.text = item.serviceCharge .toCurrencyString("₽")
    toPay.text = item.toPay() .toCurrencyString("₽")
    payButton.RoundedButtonView.setText(R.string.pay_btn_title)
}

fun BookingPriceItemBinding.bindData(
    item: BookingPriceUIModel,
    numberOfTourists: Int = 1,
    onUserAction: () -> Unit
) {
    calculateData(item)


    payButton.RoundedButtonView.setOnClickListener {

        onUserAction.invoke()


    }

}