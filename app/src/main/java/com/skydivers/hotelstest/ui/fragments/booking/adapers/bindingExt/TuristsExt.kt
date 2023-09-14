package com.skydivers.hotelstest.ui.fragments.booking.adapers.bindingExt

import android.util.Log
import android.widget.EditText
import com.google.android.material.textfield.TextInputLayout
import com.skydivers.hotelstest.R
import com.skydivers.hotelstest.databinding.TouristItemBinding



private var isFilled = false
private const val normalBackground = R.drawable.grey_rounded_background
private const val errorBackground = R.drawable.error_rounded_background

fun TouristItemBinding.getRequiredFields() = listOf(
    firstNameEditText,
    lastNameEditText,
    birthDayEditText,
    citizenshipEditText,
    passportEditText,
    passportEndDateEditText)
fun TouristItemBinding.checkThis(editText: EditText):Boolean{
    val listItems = getRequiredFields()

    for ( i in listItems.indices) {
        val requiredField = listItems[i]


        if (requiredField == editText) {
            val parent = requiredField.parent.parent as TextInputLayout

            isFilled = if (requiredField.text!!.isEmpty()) {
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
fun TouristItemBinding.checkRequires():Boolean{

    val listItems = getRequiredFields()

    val requiredList = ArrayList<Boolean>()

    for ( i in listItems.indices) {
        val requiredField = listItems[i]


        requiredList.add(checkThis(requiredField))

    }
    val result = requiredList.toList().all { it }
    Log.e(this::class.simpleName, "$isFilled")
    return result

}