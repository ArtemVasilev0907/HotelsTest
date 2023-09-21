package com.skydivers.hotelstest.booking.adapers.bindingExt

import android.app.DatePickerDialog
import android.graphics.Color
import android.telephony.PhoneNumberFormattingTextWatcher
import android.text.Editable
import android.text.Selection
import android.text.TextWatcher
import android.util.Log
import android.view.View.OnFocusChangeListener
import android.widget.EditText
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale


internal fun EditText.afterDateChanged(
    format: String = "dd.MM.yyyy",
    onSetDate: (String) -> Unit
) {

    isFocusableInTouchMode = false
    isClickable = true
    isFocusable = false


    val myCalendar = Calendar.getInstance()
    val datePickerOnDataSetListener =
        DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
            myCalendar.set(Calendar.YEAR, year)
            myCalendar.set(Calendar.MONTH, monthOfYear)
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            val sdf = SimpleDateFormat(format, Locale.getDefault())
            Log.e(this::class.simpleName, "Date:${sdf.format(myCalendar.time)}")
            setText(sdf.format(myCalendar.time))
            onSetDate.invoke(sdf.format(myCalendar.time))

        }

    this.setOnClickListener {
        DatePickerDialog(
            context, datePickerOnDataSetListener, myCalendar
                .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
            myCalendar.get(Calendar.DAY_OF_MONTH)
        ).run {
            show()
            getButton(DatePickerDialog.BUTTON_NEGATIVE).setTextColor(Color.BLACK)
            getButton(DatePickerDialog.BUTTON_POSITIVE).setTextColor(Color.BLACK)
        }
    }

}

internal fun EditText.onFocusChange(onFocusEnd: () -> Unit) {
    onFocusChangeListener = OnFocusChangeListener { _, hasFocus ->
        if (!hasFocus) {
            onFocusEnd.invoke()
        }
    }
}


internal fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
        }

        override fun afterTextChanged(editable: Editable) {
            afterTextChanged.invoke(editable.toString())
        }
    })
}

internal fun EditText.phoneNumberTextChanged(afterTextChanged: (String) -> Unit) {
    this.addTextChangedListener(object : PhoneNumberFormattingTextWatcher("RU") {

        private var mAfter = 0
        var beforeLength = 0
        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
            mAfter = after
            beforeLength = editableText.length
            Log.e(this::class.simpleName, s.toString())
            Selection.setSelection(editableText, editableText.length);


        }

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {

            val digits: Int = editableText.length

            if (beforeLength < digits && (digits == 2 || digits == 7)) {


                if (digits == 2)
                    editableText.append(" (")
                if (digits == 7)
                    editableText.append(") ")

            }
            if (beforeLength < digits && (digits == 12 || digits == 15)) {


                editableText.append("-")


            }


        }

        override fun afterTextChanged(s: Editable) {
            Selection.setSelection(editableText, editableText.length)
            if (!s.toString().contains("+") && s.toString().isNotEmpty()) {
                val text: String = s.toString().substring(0, 1)
                editableText.insert(0, s.toString().replace(text, "+7 ("))

                Selection.setSelection(editableText, editableText.length)
            }

            afterTextChanged.invoke(s.toString())
        }
    })
}