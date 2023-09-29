package com.skydivers.hotelstest.features.rooms.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.skydivers.hotelstest.core.common.UiState
import com.skydivers.hotelstest.features.rooms.presentation.di.roomsModule

import com.skydivers.hotelstest.features.rooms.presentation.model.RoomsModel
import com.skydivers.hotelstest.features.rooms.ui.R
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class RoomFragment : Fragment() {

    private val roomsViewModel by viewModel<RoomsViewModel>()
    private val loadModules by lazy {
        loadKoinModules(roomsModule)
    }

    private fun injectFeatures() = loadModules

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       injectFeatures()
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
                    onSuccessUiState(state, view)
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

    private fun onSuccessUiState(state: UiState.Success<RoomsModel>, view: View) {
        state.data.let { rooms ->
            RoomsListView(view, rooms.roomModels)
                .onBooking {
                    roomsViewModel.toBooking()
                }
                .show()
        }
    }

    private fun onErrorUiState(state: UiState.Error) {

    }

}