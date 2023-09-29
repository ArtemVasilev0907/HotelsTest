package com.skydivers.hotelstest.ui.fragments.roomss

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.skydivers.hotelstest.R
import com.skydivers.hotelstest.models.UiState
import com.skydivers.hotelstest.models.rooms.RoomsUiModel
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

        activity?.title = arguments?.getString("hotelName").toString()
        roomsViewModel.uiState.onEach { state ->

            when (state) {
                is UiState.Success -> {
                    onSuccessUiState(state,view )
                }
                is UiState.Error -> {
                    onErrorUiState(state)
                }

                is UiState.Loading -> {

                }
            }
        }.launchIn(lifecycleScope)
        return view
    }

    private fun onSuccessUiState(state: UiState.Success, view: View){
        state.data.let { rooms ->
            when(rooms) {
                is RoomsUiModel -> {
                    RoomsListView(view, rooms.roomModels)
                        .onBooking {
                            navigate(Screen.Booking, Screen.HotelRoom)
                        }
                        .show()
                }
            }
        }
    }
    private fun onErrorUiState(state: UiState.Error) {

    }

}