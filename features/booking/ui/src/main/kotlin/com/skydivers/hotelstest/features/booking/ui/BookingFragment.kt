package com.skydivers.hotelstest.features.booking.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.skydivers.hotelstest.booking.model.BookingModel
import com.skydivers.hotelstest.core.common.UiState
import com.skydivers.hotelstest.core.common.observe
import com.skydivers.hotelstest.features.booking.ui.adapers.BookingDelegateAdapter
import com.skydivers.hotelstest.features.booking.ui.databinding.FragmentBookingBinding
import com.skydivers.hotelstest.features.booking.ui.delegateAdaper.CompositeDelegateAdapter
import com.skydivers.hotelstest.features.booking.ui.di.bookingModule
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

internal class BookingFragment : Fragment() {

    private var _binding: FragmentBookingBinding? = null
    private val binding: FragmentBookingBinding
        get() = _binding!!

    private val bookingViewModel by viewModel<BookingViewModel>()

    private val loadModules by lazy {
        loadKoinModules(bookingModule)
    }

    private fun injectFeatures() = loadModules


    private val adapter = CompositeDelegateAdapter(
        BookingDelegateAdapter {
            bookingViewModel.onUserAction(it)
        }

    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectFeatures()

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBookingBinding.inflate(inflater, container, false)



        bookingViewModel.uiState.onEach { state ->
            binding.root.state = state

            state.observe(
                onLoading = { onLoading() },
                onSuccess = ::onSuccessUiState,
                onError = ::onErrorUiState
            )
        }.launchIn(lifecycleScope)

        return binding.root
    }

    private fun onSuccessUiState(state: UiState.Success<BookingModel>) {
        state.data.let { booking ->
            with(binding) {
                RecyclerView.adapter = adapter
                adapter.swapData(listOf(booking))
                RecyclerView.scrollToPosition(0)
            }
        }
    }

    private fun onErrorUiState(state: UiState.Error) {
        Log.e(state::class.simpleName, state.exception.message.orEmpty())
    }

    private fun onLoading() {
        binding.root.onReload = {
            bookingViewModel.fetchBookingData()
        }
    }


}