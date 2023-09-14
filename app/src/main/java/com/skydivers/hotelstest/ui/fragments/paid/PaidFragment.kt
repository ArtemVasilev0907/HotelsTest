package com.skydivers.hotelstest.ui.fragments.paid

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import com.skydivers.hotelstest.R
import com.skydivers.hotelstest.ui.navigation.Screen
import com.skydivers.hotelstest.ui.navigation.navigate
import kotlin.random.Random

class PaidFragment : Fragment() {




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_paid, container, false)

        val button =  view.findViewById<Button>(R.id.superButton)
        val descriptionTextView = view.findViewById<TextView>(R.id.descriptionTextView)
        val order = Random.nextInt(5000)

        descriptionTextView.text = getString(R.string.confirmation_of_an_order, order)
        button.setText(R.string.super_btn_title)
        button.setOnClickListener {
            navigate(Screen.Hotel, Screen.Paid)
        }
        return view
    }

}