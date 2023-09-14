package com.skydivers.hotelstest.ui.fragments.booking.adapers.bindingExt

import android.text.InputFilter
import android.util.Log
import android.util.Patterns
import com.skydivers.hotelstest.R
import com.skydivers.hotelstest.databinding.BuyerItemBinding
import com.skydivers.hotelstest.models.booking.BuyerInfoUIModel

private var mailIsFilled = false
private var phoneIsFilled = false
private const val normalBackground = R.drawable.grey_rounded_background
private const val errorBackground  = R.drawable.error_rounded_background


 fun BuyerItemBinding.bindData(item: BuyerInfoUIModel) {
    if (item.phone == null) {
        phoneEditText.setText("+7 (")
    }
    else{
        phoneEditText.setText(item.phone)
    }

    phoneEditText.phoneNumberTextChanged {
        item.phone = it
    }
     phoneEditText.onFocusChange {
         phoneIsFilled()
     }

    mailEditText.setText(item.email)

    mailEditText.afterTextChanged {

        item.email = it
    }
     mailEditText.onFocusChange {
         mailIsFilled()
     }
}

fun BuyerItemBinding.checkInfoFilled():Boolean  {
    mailIsFilled()
    phoneIsFilled()
    return mailIsFilled() && phoneIsFilled()
}

     fun BuyerItemBinding.mailIsFilled():Boolean{
        val mailText = mailEditText.text
        if (mailText!!.isNotEmpty()   && Patterns.EMAIL_ADDRESS.matcher(mailText).matches()){
            Log.i(this::class.simpleName, "mail is validate")
            mailTextField.setBackgroundResource(normalBackground)
            mailIsFilled = true
        } else {

            Log.e(this::class.simpleName, "mail is not valide")
            if (mailText.isNotEmpty())
            mailEditText.error = "Не корректный адрес!"
            else
                mailEditText.error = "Укажите электронную почту"
            mailIsFilled = false
            mailTextField.setBackgroundResource(errorBackground)

        }
        return mailIsFilled
    }

     fun BuyerItemBinding.phoneIsFilled():Boolean{
        if (phoneEditText.text?.length   == phoneEditText.filters.filterIsInstance<InputFilter.LengthFilter>().firstOrNull()?.max){
            phoneTextField.setBackgroundResource(normalBackground)
            phoneIsFilled = true
        }else {
            phoneIsFilled = false
            phoneEditText.error = "Не верно заполнен номер телефона!"
            phoneTextField.setBackgroundResource(errorBackground)
        }
        return phoneIsFilled
    }
