package com.skydivers.hotelstest.booking.adapers.bindingExt


import com.skydivers.hotelstest.booking.R
import com.skydivers.hotelstest.booking.databinding.BookingCostItemBinding


import com.skydivers.hotelstest.booking.model.BookingPriceUIModel

internal fun BookingCostItemBinding.calculateData(
    item: BookingPriceUIModel
) {

    tvTourPrice.text = root.context.getString(R.string.tour_currency_value, item.tourPrice)
    tvFuelCharge.text = root.context.getString(R.string.tour_currency_value, item.fuelCharge)
    tvServiceCharge.text = root.context.getString(R.string.tour_currency_value, item.serviceCharge)
    tvToPay.text = root.context.getString(R.string.tour_currency_value, item.toPay())
    btnPay.text = root.context.getString(R.string.pay_btn_title)

}

internal fun BookingCostItemBinding.bindData(
    item: BookingPriceUIModel,
    onUserAction: () -> Unit
) {
    calculateData(item)


    btnPay.setOnClickListener {

        onUserAction.invoke()


    }

}