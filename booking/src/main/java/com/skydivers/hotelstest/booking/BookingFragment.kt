package com.skydivers.hotelstest.booking

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.skydivers.hotelstest.booking.adapers.BookingDelegateAdapter
import com.skydivers.hotelstest.booking.delegateAdaper.CompositeDelegateAdapter
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

internal class BookingFragment : Fragment() {

    private val bookingViewModel by viewModel<BookingViewModel>()
    private val adapter = CompositeDelegateAdapter(
        BookingDelegateAdapter {
            bookingViewModel.onUserAction(it)
        }

    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


//        bookingViewModel.errorEvent.observe(this) {
//            Log.e(it.tag, it.message)
//        }

//        bookingViewModel.navigateTo.observe(this) {
//            navigate(it, Screen.Booking)
//        }

        lifecycleScope.launch {
            bookingViewModel.fetchBookingData()

        }


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_booking, container, false)

        lifecycleScope.launch {


            bookingViewModel.uiState.collect { state ->

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

            }
        }

        return view
    }

    private fun onSuccessUiState(state: UiState.Success, view: View) {
        state.data.let { booking ->
            Log.e("Booking", "Tourists: ${booking.tourists.size}")
            val rv = view.findViewById<RecyclerView>(R.id.RecyclerView)
            rv.adapter = adapter
            adapter.swapData(listOf(booking))
            rv.scrollToPosition(0)
        }
    }

    private fun onErrorUiState(state: UiState.Error) {


        state.errorModel.logging()
    }


}