package com.skydivers.hotelstest.ui.design.imageCarousel

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.skydivers.hotelstest.R
import com.skydivers.hotelstest.ui.fragments.hotel.AboutHotelCard

class ImageCarouselAdapter(
    private val imageURL: List<String>
) : RecyclerView.Adapter<ImageCarouselAdapter.ImageCarouselHolder>() {

    private var onItemClickListener: OnItemClickListener? = null

    interface OnItemClickListener {
        fun onItemClick(data: String, position: Int)
    }

    fun setOnItemClickListener(onItemClickListener: OnItemClickListener) {
        this.onItemClickListener = onItemClickListener
    }

    class ImageCarouselHolder(  itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image: ImageView = itemView.findViewById(R.id.imageView)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ImageCarouselHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.carousel_image_item, parent, false)
        )


    override fun onBindViewHolder(holder: ImageCarouselHolder, position: Int) = with(holder) {
        bindImage(image, imageURL[position])
        image.setOnClickListener {
            onItemClickListener?.let {

                onItemClickListener?.onItemClick(imageURL[position], position)

            }
        }

    }

    override fun getItemCount() = imageURL.size

    // @BindingAdapter("imageUrl")
    private fun bindImage(imgView: ImageView, imgUrl: String?) {
        imgUrl?.let {
            val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
            imgView.load(imgUri)
        }
    }

}