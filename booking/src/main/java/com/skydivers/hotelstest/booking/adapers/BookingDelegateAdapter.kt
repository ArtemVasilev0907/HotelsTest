package com.skydivers.hotelstest.booking.adapers


import android.util.Log
import com.skydivers.hotelstest.booking.adapers.bindingExt.*
import com.skydivers.hotelstest.booking.action.BookingUserAction
import com.skydivers.hotelstest.booking.delegateAdaper.CompositeDelegateAdapter
import com.skydivers.hotelstest.booking.delegateAdaper.ViewBindingDelegateAdapter
import com.skydivers.hotelstest.booking.model.BookingModel
import com.skydivers.hotelstest.booking.databinding.BookingHotelItemBinding


internal class BookingDelegateAdapter(private var onUserAction: ((BookingUserAction) -> Unit)? = null) :
    ViewBindingDelegateAdapter<BookingModel, BookingHotelItemBinding>(BookingHotelItemBinding::inflate) {


    override fun BookingHotelItemBinding.onBind(item: BookingModel) {

        ratingLayout.HotelRatingTextView.text = item.horating.toString()
        ratingLayout.HotelRatingDescriptionTextView.text = item.ratingName
        tvHotelName.text = item.hotelName
        tvHotelAddress.text = item.hotelAdress
        val touristDelegateAdapter = TouristDelegateAdapter()
        val adapter = CompositeDelegateAdapter(touristDelegateAdapter)
        touristRv.adapter = adapter
        adapter.swapData(item.tourists)

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
            item = item.addTouristUIModel,
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
                    onUserAction?.invoke(BookingUserAction.BuyTour)
                } else {
                    Log.e(this::class.simpleName, "Required fields Not filled!")
                }
            })


    }

    private fun CompositeDelegateAdapter.swapDataAndRefresh(data: List<Any>) {
        swapData(data)
        notifyDataSetChanged()
    }

    override fun isForViewType(item: Any) = item is BookingModel

    override fun BookingModel.getItemId(): Any = id
    override fun checkRequires() = true


}