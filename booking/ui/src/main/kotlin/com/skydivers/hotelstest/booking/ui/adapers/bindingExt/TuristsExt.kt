package com.skydivers.hotelstest.booking.ui.adapers.bindingExt

import android.widget.EditText
import com.google.android.material.textfield.TextInputLayout
import com.skydivers.hotelstest.booking.ui.R
import com.skydivers.hotelstest.booking.ui.databinding.TouristItemBinding


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

            com.skydivers.hotelstest.booking.ui.adapers.bindingExt.isFilled = if (it.text!!.isEmpty()) {
                parent.setBackgroundResource(errorBackground)
                false
            } else {
                parent.setBackgroundResource(normalBackground)
                true
            }
        }

    }

    return com.skydivers.hotelstest.booking.ui.adapers.bindingExt.isFilled
}

internal fun TouristItemBinding.checkRequires() = getRequiredFields().map { checkThis(it) }.all { it }

