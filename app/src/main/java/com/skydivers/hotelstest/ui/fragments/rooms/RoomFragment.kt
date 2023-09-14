package com.skydivers.hotelstest.ui.fragments.rooms

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.skydivers.hotelstest.R
import com.skydivers.hotelstest.ui.navigation.Screen
import com.skydivers.hotelstest.ui.navigation.navigate
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class RoomFragment : Fragment() {

    private val roomsViewModel by viewModel<RoomsViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycleScope.launch {
            roomsViewModel.fetchData()

        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_room, container, false)
//
//        view.findViewById<Button>(R.id.toBookingButton).setOnClickListener {
//            navigate(Screen.Booking, Screen.HotelRoom)
//        }


        activity?.title = arguments?.getString("hotelName").toString()
        roomsViewModel.roomsUiState.onEach { state ->

            when (state.isSuccess) {
                true -> {
                    state.result?.let { it ->

                        RoomsListView(view, it.roomModels)
                        .onBooking {
                            navigate(Screen.Booking, Screen.HotelRoom)
                        }
                            .show()

                    }
                }
                else -> {}
            }
        }.launchIn(lifecycleScope)
        return view
    }

}