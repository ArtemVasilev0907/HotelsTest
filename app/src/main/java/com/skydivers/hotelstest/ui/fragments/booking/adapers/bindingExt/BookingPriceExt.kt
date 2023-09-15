package com.skydivers.hotelstest.ui.fragments.booking.adapers.bindingExt

import com.skydivers.hotelstest.R
import com.skydivers.hotelstest.databinding.BookingPriceItemBinding
import com.skydivers.hotelstest.models.booking.BookingPriceUIModel



fun BookingPriceItemBinding.bindData(
    item: BookingPriceUIModel,
    onUserAction: () -> Unit
) {
    tourPrice.text = item.tourPrice .toCurrencyString("₽")
    fuelCharge.text = item.fuelCharge .toCurrencyString("₽")
    serviceCharge.text = item.serviceCharge .toCurrencyString("₽")
    toPay.text = item.toPay() .toCurrencyString("₽")
    payButton.RoundedButtonView.setText(R.string.pay_btn_title)


    payButton.RoundedButtonView.setOnClickListener {

        onUserAction.invoke()


    }

}