package com.skydivers.hotelstest.ui.fragments.hotel

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.skydivers.hotelstest.R
import com.skydivers.hotelstest.core.theme.design.views.imageCarousel.ImageCarouselView
import com.skydivers.hotelstest.databinding.HotelNameLayoutBinding

import com.skydivers.hotelstest.databinding.PriceLayoutBinding
import com.skydivers.hotelstest.databinding.RatingLayoutBinding


class HotelBaseCard constructor(
    private val view: View
) {

    private val parent: ViewGroup = view.findViewById<LinearLayout>(R.id.baseCardLayout)
    private val layoutInflater = LayoutInflater.from(parent.context)!!
    private val rl  = view.findViewById<LinearLayout>(R.id.baseCardLayout).removeAllViews()




    fun addImageCarousel(imagesUrls: List<String>): HotelBaseCard {



        ImageCarouselView(
            parent,
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

    fun addHotelRating(rating: Int, ratingDescription: String = ""): HotelBaseCard {


        RatingLayoutBinding.inflate(layoutInflater, parent, true)
        with(RatingLayoutBinding.bind(parent)){
            HotelRatingTextView.text = rating.toString()
            HotelRatingDescriptionTextView.text = ratingDescription
        }
        return this
    }

    fun addPrice(minimalPrice: String, priceForIt: String): HotelBaseCard {

        PriceLayoutBinding.inflate(layoutInflater, parent, true)
        with(PriceLayoutBinding.bind(parent)){
            HotelRoomPriceTextView.text = minimalPrice
            HotelRoomPriceDescription.text = priceForIt
        }

        return this
    }

    fun addTitle(title: String, address: String): HotelBaseCard {

        HotelNameLayoutBinding.inflate(layoutInflater, parent, true)
        with(HotelNameLayoutBinding.bind(parent)){
            HotelNameTextView.text = title
            if (address.isEmpty())
                Address.visibility = View.GONE
            else
                Address.text = address
        }

        return this
    }


}