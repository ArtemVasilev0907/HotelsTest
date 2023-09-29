package com.skydivers.hotelstest.features.rooms.presentation.fragment

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import com.skydivers.hotelstest.core.theme.design.views.chips.ChipsGridView
import com.skydivers.hotelstest.core.theme.design.views.imageCarousel.ImageCarouselView

import com.skydivers.hotelstest.features.rooms.ui.R



class RoomsBaseCard constructor(
    private val view: View
) {

    private val parent: ViewGroup = view.findViewById<LinearLayout>(R.id.roundedCardLayout)
    private val rl  = view.findViewById<LinearLayout>(R.id.roundedCardLayout).removeAllViews()
    private var onBooking: ((value: Int) -> Unit)? = null
    val context: Context = parent.context

    fun addImageCarousel(imagesUrls: List<String>): RoomsBaseCard {



        LayoutInflater.from(
            parent.context
        ).inflate(R.layout.image_carousel_layout, parent, true)

        ImageCarouselView(
            view,
            imagesUrls
        )
            .addProgressBar(parent)
            .onChangePosition { position ->
                Log.i(this::class.simpleName, "Position:${position}")

            }.onClickItem { data, position ->
                Log.i(this::class.simpleName, "Click Position:${position} : $data")
            }.show()
        return this
    }


    fun addChips(chips: List<String>): RoomsBaseCard {

        ChipsGridView(view,chips)
        return this
    }

    fun addPrice(minimalPrice: String, priceForIt: String): RoomsBaseCard {
        LayoutInflater.from(
            parent.context
        ).inflate(R.layout.price_layout, parent, true)
        view.findViewById<TextView>(R.id.HotelRoomPriceTextView).text =
            minimalPrice
        view.findViewById<TextView>(R.id.HotelRoomPriceDescription).text = priceForIt
        return this
    }

    fun addTitle(title: String, address: String =""): RoomsBaseCard {

        LayoutInflater.from(
            parent.context
        ).inflate(R.layout.hotel_name_layout, parent, true)
        view.findViewById<TextView>(R.id.HotelNameTextView).text = title
        if (address.isEmpty()) {
            view.findViewById<TextView>(R.id.Address).visibility = View.GONE
        } else {
            view.findViewById<TextView>(R.id.Address).text = address
        }
        return this
    }

    fun addAboutRoom(): RoomsBaseCard {
        LayoutInflater.from(
            parent.context
        ).inflate(R.layout.about_room_button_view, parent, true)
        return this
    }

    fun addBookingButton(title:String = parent.resources.getString(R.string.select_room)): RoomsBaseCard {
        LayoutInflater.from(
            parent.context
        ).inflate(R.layout.rounded_button_view, parent, true)
        val bookingButton = view.findViewById<Button>(R.id.RoundedButtonView)
        bookingButton.text = title
        bookingButton.setOnClickListener{
            onBooking?.invoke(0)
        }
        return this
    }

    fun onBooking(onBooking:(value:Int)->Unit): RoomsBaseCard {
        this.onBooking = onBooking
        return this
    }


}