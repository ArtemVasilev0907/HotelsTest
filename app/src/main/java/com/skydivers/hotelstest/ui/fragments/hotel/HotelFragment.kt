package com.skydivers.hotelstest.ui.fragments.hotel


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.skydivers.hotelstest.R
import com.skydivers.hotelstest.databinding.FragmentHotelBinding
import com.skydivers.hotelstest.models.UiState
import com.skydivers.hotelstest.models.hotel.HotelsUiModel
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
        val fragmentHotel = FragmentHotelBinding.inflate(inflater, container, false)



        hotelViewModel.uiState.onEach { state ->

            when (state) {
                is UiState.Success -> {
                    onSuccessUiState(state,fragmentHotel )
                }
                is UiState.Error -> {
                    onErrorUiState(state)
                }

                is UiState.Loading -> {

                }
            }
        }.launchIn(lifecycleScope)
        return fragmentHotel.root
    }

    private fun onSuccessUiState(state: UiState.Success, fragmentHotel: FragmentHotelBinding){
        state.data.let { hotel ->
            when(hotel){
                is HotelsUiModel->{
                    HotelBaseCard( fragmentHotel.root)
                        .addImageCarousel(imagesUrls = hotel.imageUrls)
                        .addHotelRating(
                            rating = hotel.rating,
                            ratingDescription = hotel.ratingName
                        )
                        .addTitle(title = hotel.name, address = hotel.address)
                        .addPrice(
                            minimalPrice = getString(R.string.minimal_price,hotel.minimalPrice),
                            priceForIt = hotel.priceForIt
                        )
                    AboutHotelCard(fragmentHotel.root)
                        .addTitle( getString(R.string.about_hotel))
                        .addChips(hotel.aboutTheHotel.peculiarities)
                        .addButtons()
                        .addDescription(hotel.aboutTheHotel.description)
                    ButtonCard(fragmentHotel.root).addButton(resources.getString(R.string.to_select_room))
                        .onButtonClick {
                            val hotelNameBundle = Bundle()
                            hotelNameBundle.putString("hotelName", hotel.name)
                            navigate(Screen.HotelRoom, Screen.Hotel,hotelNameBundle )
                        }


                }

                else -> {}
            }


        }
    }
    private fun onErrorUiState(state: UiState.Error) {

    }


    override fun onStart() {
        super.onStart()

    }


}