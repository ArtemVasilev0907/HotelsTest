package com.skydivers.hotelstest.ui.fragments.booking.adapers


import android.util.Log

import com.skydivers.hotelstest.databinding.BookingTopItemBinding
import com.skydivers.hotelstest.models.booking.BookingModel
import com.skydivers.hotelstest.ui.action.BookingUserAction
import com.skydivers.hotelstest.ui.design.delegateAdaper.CompositeDelegateAdapter
import com.skydivers.hotelstest.ui.design.delegateAdaper.ViewBindingDelegateAdapter
import com.skydivers.hotelstest.ui.fragments.booking.adapers.bindingExt.*


class BookingDelegateAdapter(private var onUserAction: ((BookingUserAction) -> Unit)? = null) :
    ViewBindingDelegateAdapter<BookingModel, BookingTopItemBinding>(BookingTopItemBinding::inflate) {


    override fun BookingTopItemBinding.onBind(item: BookingModel) {

        ratingLayout.HotelRatingTextView.text = item.horating.toString()
        ratingLayout.HotelRatingDescriptionTextView.text = item.ratingName
        hotellayout.HotelNameTextView.text = item.hotelName
        hotellayout.Address.text = item.hotelAdress
        val touristDelegateAdapter = TouristDelegateAdapter()
        val adapter = CompositeDelegateAdapter(touristDelegateAdapter)
        touristRv.adapter = adapter
        adapter.swapData(item.tourists)

        bookingBlockLayout.bind(item)

        buyer.bindData(item.buyerInfo)

        addTourist.bindData(
            item = item.addTouristUIModel,
            onUserAction = {

                onUserAction?.invoke(it)
                adapter.swapData(item.tourists)
                bookingPrice.calculateData(
                    item.bookingPriceUIModel)

            }

        )

        bookingPrice.bindData(item.bookingPriceUIModel,
            onUserAction = {
                val list = adapter.findAdapters(touristDelegateAdapter).map {
                    it
                }

                val listOfTourists = ArrayList<Boolean>()
                var touristListIsField = false
                if (list.isNotEmpty())
                    for (i in list.indices) {
                        if (list[i] is TouristDelegateAdapter) {
                            listOfTourists.add( list[i].checkRequires())
                            adapter.notifyDataSetChanged()
                        }


                    }
                if (listOfTourists.isNotEmpty()){
                    touristListIsField = listOfTourists.toList().all { it }
                }
                Log.e(listOfTourists::class.simpleName, touristListIsField.toString())
                if (buyer.checkInfoFilled() && touristListIsField ) {
                    onUserAction?.invoke(BookingUserAction.BuyTour)
                } else {
                    Log.e(this::class.simpleName, "Required fields Not filled!")
                }
            })






    }


    override fun isForViewType(item: Any) = item is BookingModel

    override fun BookingModel.getItemId(): Any = id
    override fun checkRequires() = true


}