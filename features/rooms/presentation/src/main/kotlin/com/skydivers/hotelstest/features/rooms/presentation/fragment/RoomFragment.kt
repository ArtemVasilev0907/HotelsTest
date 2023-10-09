package com.skydivers.hotelstest.features.rooms.presentation.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.skydivers.hotelstest.core.common.UiState
import com.skydivers.hotelstest.core.common.observe
import com.skydivers.hotelstest.features.rooms.presentation.di.roomsModule
import com.skydivers.hotelstest.features.rooms.presentation.model.RoomsModel
import com.skydivers.hotelstest.features.rooms.ui.databinding.FragmentRoomBinding
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class RoomFragment : Fragment() {

    private val roomsViewModel by viewModel<RoomsViewModel>()
    private val loadModules by lazy {
        loadKoinModules(roomsModule)
    }

    private fun injectFeatures() = loadModules

    private var _binding: FragmentRoomBinding? = null
    private val binding: FragmentRoomBinding
        get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectFeatures()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentRoomBinding.inflate(inflater, container, false)

        activity?.title = arguments?.getString("hotelName").toString()

        lifecycleScope.launch {

            roomsViewModel.uiState.collect { state ->
                binding.root.state = state


                state.observe(
                    onSuccess = {
                        onSuccessUiState(it, binding.root)
                    },
                    onError = {
                        onErrorUiState(it)
                    }
                )
            }
        }
        return binding.root
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
        Log.e(state::class.simpleName, state.exception.message.orEmpty())
        binding.root.onReload = {
            roomsViewModel.fetchData()
        }
    }

}