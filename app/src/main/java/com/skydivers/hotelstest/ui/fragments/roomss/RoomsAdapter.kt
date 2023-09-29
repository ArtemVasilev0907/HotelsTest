package com.skydivers.hotelstest.ui.fragments.roomss

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.skydivers.hotelstest.R
import com.skydivers.hotelstest.models.rooms.RoomModel

class RoomsAdapter(
    private val rooms: List<RoomModel>
) : RecyclerView.Adapter<RoomsAdapter.RoomsViewHolder>() {

    private var onItemClickListener: OnItemClickListener? = null
    interface OnItemClickListener {
        fun onSelectRoom(bookingId: Int)
    }

    fun setOnItemClickListener(onItemClickListener: OnItemClickListener) {
        this.onItemClickListener = onItemClickListener
    }
    class RoomsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val room = RoomsBaseCard(itemView)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        RoomsViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.rounded_card_layout, parent, false)
        )



    override fun onBindViewHolder(holder: RoomsViewHolder, position: Int)  {
        holder.room.addImageCarousel(rooms[position].imageUrls)
        holder.room.addTitle(rooms[position].name)
        holder.room.addChips(rooms[position].peculiarities)
        val price = holder.room.context.getString(R.string.minimal_price, rooms[position].price)
        holder.room.addAboutRoom()
        holder.room.addPrice(price, rooms[position].pricePer)
        holder.room.addBookingButton().onBooking {

                onItemClickListener?.let {

                    onItemClickListener?.onSelectRoom(rooms[position].id)

                }

        }
    }

    override fun getItemCount() = rooms.size



}