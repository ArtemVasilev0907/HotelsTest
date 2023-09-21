package com.skydivers.hotelstest.booking.adapers.bindingExt

import android.text.InputFilter
import android.util.Log
import android.util.Patterns
import com.skydivers.hotelstest.booking.R
import com.skydivers.hotelstest.booking.databinding.BookingBuyerItemBinding
import com.skydivers.hotelstest.booking.model.BuyerInfoUIModel


private var mailIsFilled = false
private var phoneIsFilled = false
private val normalBackground = R.drawable.grey_rounded_background
private val errorBackground = R.drawable.error_rounded_background


internal fun BookingBuyerItemBinding.bindData(item: BuyerInfoUIModel) {
    if (item.phone == null) {
        etPhone.setText("+7 (")
    } else {
        etPhone.setText(item.phone)
    }

    etPhone.phoneNumberTextChanged {
        item.phone = it
    }
    etPhone.onFocusChange {
        phoneIsFilled()
    }

    etMail.setText(item.email)

    etMail.afterTextChanged {

        item.email = it
    }
    etMail.onFocusChange {
        mailIsFilled()
    }
}

internal fun BookingBuyerItemBinding.checkInfoFilled(): Boolean {
    mailIsFilled()
    phoneIsFilled()
    return mailIsFilled() && phoneIsFilled()
}

internal fun BookingBuyerItemBinding.mailIsFilled(): Boolean {
    val mailText = etMail.text
    if (mailText!!.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(mailText).matches()) {
        Log.i(this::class.simpleName, "mail is validate")
        mailTextField.setBackgroundResource(normalBackground)
        mailIsFilled = true
    } else {

        Log.e(this::class.simpleName, "mail is not valid")
        if (mailText.isNotEmpty())
            etMail.error = "Не корректный адрес!"
        else
            etMail.error = "Укажите электронную почту"
        mailIsFilled = false
        mailTextField.setBackgroundResource(errorBackground)

    }
    return mailIsFilled
}

internal fun BookingBuyerItemBinding.phoneIsFilled(): Boolean {
    if (etPhone.text?.length == etPhone.filters.filterIsInstance<InputFilter.LengthFilter>()
            .firstOrNull()?.max
    ) {
        phoneTextField.setBackgroundResource(normalBackground)
        phoneIsFilled = true
    } else {
        phoneIsFilled = false
        etPhone.error = "Не верно заполнен номер телефона!"
        phoneTextField.setBackgroundResource(errorBackground)
    }
    return phoneIsFilled
}
