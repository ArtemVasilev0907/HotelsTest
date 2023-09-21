package com.skydivers.hotelstest.booking.adapers.bindingExt

import android.widget.EditText
import com.google.android.material.textfield.TextInputLayout
import com.skydivers.hotelstest.booking.R
import com.skydivers.hotelstest.booking.databinding.TouristItemBinding


private var isFilled = false
private val normalBackground = R.drawable.grey_rounded_background
private val errorBackground = R.drawable.error_rounded_background

internal fun TouristItemBinding.getRequiredFields() = listOf(
    etFirstName,
    etLastName,
    etBirthDay,
    etCitizenship,
    etPassport,
    etPassportEndDate
)

internal fun TouristItemBinding.checkThis(editText: EditText): Boolean {

    getRequiredFields().map {
        if (it == editText) {
            val parent = it.parent.parent as TextInputLayout

            isFilled = if (it.text!!.isEmpty()) {
                parent.setBackgroundResource(errorBackground)
                false
            } else {
                parent.setBackgroundResource(normalBackground)
                true
            }
        }

    }

    return isFilled
}

internal fun TouristItemBinding.checkRequires() = getRequiredFields().map { checkThis(it) }.all { it }

