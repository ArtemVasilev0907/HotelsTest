package com.skydivers.hotelstest.ui.fragments.booking

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.RecyclerView
import com.skydivers.hotelstest.R
import com.skydivers.hotelstest.models.BaseUIModel
import com.skydivers.hotelstest.ui.design.delegateAdaper.CompositeDelegateAdapter
import com.skydivers.hotelstest.ui.fragments.booking.adapers.*
import com.skydivers.hotelstest.ui.navigation.Screen
import com.skydivers.hotelstest.ui.navigation.navigate
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class BookingFragment : Fragment() {

    private val bookingViewModel by viewModel<BookingViewModel>()
    private val adapter = CompositeDelegateAdapter(
        BookingDelegateAdapter {
                bookingViewModel.onUserAction(it)
        }

    )



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycleScope.launch {
            bookingViewModel.fetchBookingData()

        }
        bookingViewModel.errorEvent.observe(this){
            Log.e(it.tag, it.message)
        }

        bookingViewModel.navigateTo.observe(this){
            navigate(it, Screen.Booking)
        }



    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_booking, container, false)






        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED) {

                bookingViewModel.booking.collect {bookingUiState ->

                    when (bookingUiState.isSuccess) {
                        true -> {
                            bookingUiState.result?.let {
                                // New value received
                                val bookingList = listOf(it)

                                val rv = view.findViewById<RecyclerView>(R.id.RecyclerView)
                                rv.adapter = adapter
                                adapter.swapData(bookingList)
                                rv.scrollToPosition(0)
                            }
                        }
                        else -> {}
                    }

                }

            }
        }


        return view
    }






}