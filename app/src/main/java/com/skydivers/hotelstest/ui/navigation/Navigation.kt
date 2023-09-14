

package com.skydivers.hotelstest.ui.navigation



import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.skydivers.hotelstest.R

import java.security.InvalidParameterException


fun Fragment.navigate(to: Screen, from: Screen) {
    if (to == from) {
        throw InvalidParameterException("Can't navigate to $to")
    }
    when (to) {
        Screen.Hotel -> {
            from(from)
            findNavController().navigate(R.id.navigation_hotel)
        }
        Screen.HotelRoom -> {
            findNavController().navigate(R.id.action_navigation_hotel_to_room)
        }
        Screen.Booking -> {
            findNavController().navigate(R.id.action_navigation_room_to_booking)
        }
        Screen.Paid -> {
            from(from)
            findNavController().navigate(R.id.action_navigation_booking_to_paid)
        }

    }
}
fun Fragment.navigate(to: Screen, from: Screen, bundle: Bundle) {
    if (to == from) {
        throw InvalidParameterException("Can't navigate to $to")
    }
    when (to) {
        Screen.Hotel -> {
            from(from)
            findNavController().navigate(R.id.navigation_hotel)
        }
        Screen.HotelRoom -> {
            findNavController().navigate(R.id.action_navigation_hotel_to_room,bundle )
        }
        Screen.Booking -> {
            findNavController().navigate(R.id.action_navigation_room_to_booking)
        }
        Screen.Paid -> {
            from(from)
            findNavController().navigate(R.id.action_navigation_booking_to_paid)
        }

    }
}
private fun Fragment.from (from : Screen){
    when(from) {

        Screen.Paid -> {
            findNavController().popBackStack(R.id.navigation_room, true)
            findNavController().popBackStack(R.id.navigation_booking, true)
            findNavController().popBackStack(R.id.navigation_paid, true)

        }

        else -> {

        }
    }
}




