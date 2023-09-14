package com.skydivers.hotelstest.ui.fragments.hotel


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.skydivers.hotelstest.R
import com.skydivers.hotelstest.models.ErrorUIModel
import com.skydivers.hotelstest.ui.navigation.Screen
import com.skydivers.hotelstest.ui.navigation.navigate
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel


class HotelFragment : Fragment() {

    private val hotelViewModel by viewModel<HotelViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycleScope.launch {
            hotelViewModel.fetchHotelData()

        }
        hotelViewModel.errorEvent.observe(this){
            Log.e(it.tag, it.message)
        }

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_hotel, container, false)

//        view.findViewById<Button>(R.id.toRoomButton).setOnClickListener {
//            navigate(Screen.HotelRoom, Screen.Hotel)
//        }

        hotelViewModel.hotelUiState.onEach { hotelsUiState ->

            when (hotelsUiState.isSuccess) {
                true -> {
                    hotelsUiState.result?.let { aboutHotel ->
                        HotelBaseCard( view)
                            .addImageCarousel(imagesUrls = aboutHotel.imageUrls)
                            .addHotelRating(
                                rating = aboutHotel.rating,
                                ratingDescription = aboutHotel.ratingName
                            )
                            .addTitle(title = aboutHotel.name, address = aboutHotel.address)
                            .addPrice(
                                minimalPrice = "от ${aboutHotel.minimalPrice} ₽",
                                priceForIt = aboutHotel.priceForIt
                            )
                        AboutHotelCard(view)
                            .addTitle( getString(R.string.about_hotel))
                            .addChips(aboutHotel.aboutTheHotel.peculiarities)
                            .addButtons()
                            .addDescription(aboutHotel.aboutTheHotel.description)
                        ButtonCard(view).addButton(resources.getString(R.string.to_select_room))
                            .onButtonClick {
                                val hotelNameBundle = Bundle()
                                hotelNameBundle.putString("hotelName", aboutHotel.name)
                                navigate(Screen.HotelRoom, Screen.Hotel,hotelNameBundle )
                            }


                    }
                }
                else -> {
                    when(hotelsUiState.error){
                        is ErrorUIModel ->{
                            Log.e(hotelsUiState.error!!.tag, hotelsUiState.error!!.message)
                        }
                    }
                }
            }
        }.launchIn(lifecycleScope)
        return view
    }


    override fun onStart() {
        super.onStart()

    }


}