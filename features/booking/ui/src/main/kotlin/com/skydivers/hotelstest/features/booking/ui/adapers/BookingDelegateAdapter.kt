package com.skydivers.hotelstest.features.booking.ui.adapers


import android.util.Log
import androidx.recyclerview.widget.SimpleItemAnimator
import com.skydivers.hotelstest.booking.model.BookingModel
import com.skydivers.hotelstest.features.booking.ui.adapers.bindingExt.*
import com.skydivers.hotelstest.features.booking.ui.repositories.BookingUserAction
import com.skydivers.hotelstest.features.booking.ui.delegateAdaper.CompositeDelegateAdapter
import com.skydivers.hotelstest.features.booking.ui.delegateAdaper.ViewBindingDelegateAdapter

import com.skydivers.hotelstest.features.booking.ui.adapers.bindingExt.bindData
import com.skydivers.hotelstest.features.booking.ui.adapers.bindingExt.checkInfoFilled
import com.skydivers.hotelstest.features.booking.ui.databinding.BookingHotelItemBinding



internal class BookingDelegateAdapter(private var onUserAction: ((BookingUserAction) -> Unit)? = null) :
    ViewBindingDelegateAdapter<BookingModel, BookingHotelItemBinding>(BookingHotelItemBinding::inflate) {


    override fun BookingHotelItemBinding.onBind(item: BookingModel) {


        llRating.tvHotelRating.text = item.horating.toString()
        llRating.tvHotelRatingDescription.text = item.ratingName
        tvHotelName.text = item.hotelName
        tvHotelAddress.text = item.hotelAdress
        val touristDelegateAdapter = TouristDelegateAdapter()
        val adapter = CompositeDelegateAdapter(touristDelegateAdapter)
        touristRv.adapter = adapter
        (touristRv.itemAnimator as SimpleItemAnimator).supportsChangeAnimations = true


        touristDelegateAdapter.onUserAction = {
            val index = it as BookingUserAction.DeleteTourist
            onUserAction?.invoke(it)
            adapter.swapDataAndRefresh(index.touristId,item.tourists)
            bookingCost.calculateData(
                item.bookingPriceUIModel!!
            )
        }


        tourBlock.bindData(item)

        buyer.bindData(item.buyerInfo,
            onBuyerFormEvent = {onUserAction?.invoke(it)})

        addTourist.bindData(
            onUserAction = {

                onUserAction?.invoke(it)
                adapter.swapDataAndRefresh(item.tourists.size,item.tourists)
                bookingCost.calculateData(
                    item.bookingPriceUIModel!!
                )

            }

        )



        bookingCost.bindData(item.bookingPriceUIModel!!,
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

    private fun CompositeDelegateAdapter.swapDataAndRefresh(fromIndex:Int, data: List<Any>) {

            swapData(data)
            data.mapIndexed { index, any ->
                notifyItemChanged(index , any )
            }


    }

    override fun isForViewType(item: Any) = item is BookingModel

    override fun BookingModel.getItemId(): Any = id
    override fun checkRequires() = true


}