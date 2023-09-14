package com.skydivers.hotelstest.ui.fragments.hotel

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import com.skydivers.hotelstest.R

class ButtonCard constructor(

    private val view: View
) {

    private val removeAll = view.findViewById<LinearLayout>(R.id.buttonCardLayout).removeAllViews()
    private val parent:ViewGroup = view.findViewById<LinearLayout>(R.id.buttonCardLayout)
    private var onButtonClick: ((value: Int) -> Unit)? = null

    fun addButton(title:String):ButtonCard{
        LayoutInflater.from(
            parent.context
        ).inflate(R.layout.rounded_button_view, parent, true)
        val button = view.findViewById<Button>(R.id.RoundedButtonView)
        button.text = title
        button.setOnClickListener{
            onButtonClick?.invoke(0)
        }
        return this
    }

    fun onButtonClick(onButtonClick:(value:Int)->Unit):ButtonCard{
        this.onButtonClick = onButtonClick
        return this
    }

}