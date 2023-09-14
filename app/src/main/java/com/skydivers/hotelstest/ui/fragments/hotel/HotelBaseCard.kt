package com.skydivers.hotelstest.ui.fragments.hotel

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.skydivers.hotelstest.R
import com.skydivers.hotelstest.ui.design.imageCarousel.ImageCarouselView

class HotelBaseCard constructor(
    private val view: View
) {

    private val parent: ViewGroup = view.findViewById<LinearLayout>(R.id.baseCardLayout)
    private val rl  = view.findViewById<LinearLayout>(R.id.baseCardLayout).removeAllViews()

    fun addImageCarousel(imagesUrls: List<String>): HotelBaseCard {



        LayoutInflater.from(
            parent.context
        ).inflate(R.layout.image_carousel_layout, parent, true)

        ImageCarouselView(
            view,
            imagesUrls
        )
            .addProgressBar()
            .onChangePosition { position ->
                Log.i(this::class.simpleName, "Position:${position}")

            }.onClickItem { data, position ->
                Log.i(this::class.simpleName, "Click Position:${position} : $data")
            }.show()
        return this
    }

    fun addHotelRating(rating: Int, ratingDescription: String = ""): HotelBaseCard {

        LayoutInflater.from(
            parent.context
        ).inflate(R.layout.rating_layout, parent, true)
        view.findViewById<TextView>(R.id.HotelRatingTextView).text =
            rating.toString()
        view.findViewById<TextView>(R.id.HotelRatingDescriptionTextView).text =
            ratingDescription
        return this
    }

    fun addPrice(minimalPrice: String, priceForIt: String): HotelBaseCard {
        LayoutInflater.from(
            parent.context
        ).inflate(R.layout.price_layout, parent, true)
        view.findViewById<TextView>(R.id.HotelRoomPriceTextView).text =
            minimalPrice
        view.findViewById<TextView>(R.id.HotelRoomPriceDescription).text = priceForIt
        return this
    }

    fun addTitle(title: String, address: String): HotelBaseCard {

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


}