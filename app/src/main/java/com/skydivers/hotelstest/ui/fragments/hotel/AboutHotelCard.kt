package com.skydivers.hotelstest.ui.fragments.hotel


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.skydivers.hotelstest.R
import com.skydivers.hotelstest.ui.design.buttonList.ButtonsView
import com.skydivers.hotelstest.ui.design.chips.ChipsGridView



class AboutHotelCard constructor(
    private val view: View
) {

    private val parent:ViewGroup = view.findViewById<LinearLayout>(R.id.roundedCardLayout)
    private val rl =  view.findViewById<LinearLayout>(R.id.roundedCardLayout).removeAllViews()


    fun addTitle(title :String):AboutHotelCard{

        LayoutInflater.from(parent.context
        ).inflate(R.layout.title_text_view, parent, true)
        view.findViewById<TextView>(R.id.TitleTextView).text =
            title
        return this
    }

    fun addChips(chips: List<String>):AboutHotelCard{


        LayoutInflater.from(parent.context
        ).inflate(R.layout.chips_grid_layout, parent, true)
        ChipsGridView(view,chips).init()
        return this
    }

    fun addButtons():AboutHotelCard{

        LayoutInflater.from(parent.context
        ).inflate(R.layout.buttons_list_layout, parent, true)
        ButtonsView(view).init()
        return this
    }

    fun addDescription(description : String):AboutHotelCard{
        LayoutInflater.from(parent.context
        ).inflate(R.layout.description_text_view, parent, true)
        view.findViewById<TextView>(R.id.DescriptionTextView).text =
            description
        return this
    }

}