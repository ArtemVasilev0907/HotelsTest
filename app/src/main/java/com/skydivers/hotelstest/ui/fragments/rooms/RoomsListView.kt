package com.skydivers.hotelstest.ui.fragments.rooms

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView
import com.skydivers.hotelstest.R
import com.skydivers.hotelstest.models.rooms.RoomModel


class RoomsListView constructor(
    private val view: View,
    private val rooms: List<RoomModel>
) {

    private val parent: ViewGroup = view.findViewById<LinearLayout>(R.id.roomsBaseLayout)
    private val rl =  view.findViewById<LinearLayout>(R.id.roomsBaseLayout).removeAllViews()
    private var onSelectRoom: ((value: Int) -> Unit)? = null

    fun show() {
        LayoutInflater.from(parent.context
        ).inflate(R.layout.rooms_list_layout, parent, true)
         val roomsRecycler = view.findViewById<RecyclerView>(R.id.RoomsRecyclerView)
       val  roomsAdapter = RoomsAdapter(rooms)
        roomsRecycler.adapter = roomsAdapter

        roomsAdapter.setOnItemClickListener(object :
            RoomsAdapter.OnItemClickListener {
            override fun onSelectRoom(bookingId: Int) {
                onSelectRoom?.invoke(bookingId)
            }


        })

    }

    fun onBooking(onBooking:(value:Int)->Unit):RoomsListView{
        this.onSelectRoom = onBooking
        return this
    }


}