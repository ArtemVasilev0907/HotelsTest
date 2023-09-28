package com.skydivers.hotelstest.booking.ui.adapers


import android.util.Log
import com.skydivers.hotelstest.booking.ui.adapers.bindingExt.*
import com.skydivers.hotelstest.booking.ui.repositories.BookingUserAction
import com.skydivers.hotelstest.booking.ui.delegateAdaper.CompositeDelegateAdapter
import com.skydivers.hotelstest.booking.ui.delegateAdaper.ViewBindingDelegateAdapter
import com.skydivers.hotelstest.booking.ui.databinding.BookingHotelItemBinding
import com.skydivers.hotelstest.booking.ui.adapers.bindingExt.bindData
import com.skydivers.hotelstest.booking.ui.adapers.bindingExt.checkInfoFilled


internal class BookingDelegateAdapter(private var onUserAction: ((BookingUserAction) -> Unit)? = null) :
    ViewBindingDelegateAdapter<com.skydivers.hotelstest.booking.model.BookingModel, BookingHotelItemBinding>(BookingHotelItemBinding::inflate) {


    override fun BookingHotelItemBinding.onBind(item: com.skydivers.hotelstest.booking.model.BookingModel) {

        ratingLayout.HotelRatingTextView.text = item.horating.toString()
        ratingLayout.HotelRatingDescriptionTextView.text = item.ratingName
        tvHotelName.text = item.hotelName
        tvHotelAddress.text = item.hotelAdress
        val touristDelegateAdapter = TouristDelegateAdapter()
        val adapter = CompositeDelegateAdapter(touristDelegateAdapter)
        touristRv.adapter = adapter


        touristDelegateAdapter.onUserAction = {
            onUserAction?.invoke(it)
            adapter.swapDataAndRefresh(item.tourists)
            bookingCost.calculateData(
                item.bookingPriceUIModel
            )
        }


        tourBlock.bindData(item)

        buyer.bindData(item.buyerInfo)

        addTourist.bindData(
            onUserAction = {

                onUserAction?.invoke(it)
                adapter.swapDataAndRefresh(item.tourists)
                bookingCost.calculateData(
                    item.bookingPriceUIModel
                )

            }

        )



        bookingCost.bindData(item.bookingPriceUIModel,
            onUserAction = {
                val list = adapter.findAdapters(touristDelegateAdapter).map {
                    it
                }

                val listOfTourists = ArrayList<Boolean>()
                var touristListIsField = false
                if (list.isNotEmpty()) list.map {
                    listOfTourists.add(it.checkRequires())
                    adapter.notifyDataSetChanged()
                }

                if (listOfTourists.isNotEmpty()) {
                    touristListIsField = listOfTourists.toList().all { it }
                }
                Log.e(listOfTourists::class.simpleName, touristListIsField.toString())
                if (buyer.checkInfoFilled() && touristListIsField) {
                    onUserAction?.invoke(com.skydivers.hotelstest.booking.ui.repositories.BookingUserAction.BuyTour)
                } else {
                    Log.e(this::class.simpleName, "Required fields Not filled!")
                }
            })


    }

    private fun CompositeDelegateAdapter.swapDataAndRefresh(data: List<Any>) {

            swapData(data)
            notifyItemChanged(data.lastIndex)

    }

    override fun isForViewType(item: Any) = item is com.skydivers.hotelstest.booking.model.BookingModel

    override fun com.skydivers.hotelstest.booking.model.BookingModel.getItemId(): Any = id
    override fun checkRequires() = true


}