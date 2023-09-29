package com.skydivers.hotelstest.core.theme.design.views.imageCarousel

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.skydivers.hotelstest.core.theme.design.views.extensions.loadUrl
import com.skydivers.theme.R


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

        val image: ImageView = itemView.findViewById(R.id.ivCarousel)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ImageCarouselHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.carousel_image_item, parent, false)
        )


    override fun onBindViewHolder(holder: ImageCarouselHolder, position: Int) = with(holder) {

        image.loadUrl(imageURL[position])
        image.setOnClickListener {
            onItemClickListener?.let {

                onItemClickListener?.onItemClick(imageURL[position], position)

            }
        }

    }

    override fun getItemCount() = imageURL.size



}