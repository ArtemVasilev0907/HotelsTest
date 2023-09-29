package com.skydivers.hotelstest.core.theme.design.views.imageCarousel

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SnapHelper


import com.skydivers.theme.R
import com.skydivers.theme.databinding.ImageCarouselLayoutBinding


class ImageCarouselView constructor(
    private val view: View,
    private val imageUrls: List<String>
) {

    private var onChangePosition: ((position: Int) -> Unit)? = null
    private var onClick: ((data: String, position: Int) -> Unit)? = null
    private var circlesProgressBarView: CirclesProgressBarView? = null
    private var binding:ImageCarouselLayoutBinding

    init {
        val inflater = LayoutInflater.from(view.context)
        binding = ImageCarouselLayoutBinding.inflate(inflater, view  as ViewGroup, false)
        view.addView(binding.root)
    }

    fun show() {

        val imageCarouselAdapter = ImageCarouselAdapter(imageUrls)

        binding.imageCarouselRecyclerView.adapter = imageCarouselAdapter


        imageCarouselAdapter.setOnItemClickListener(object :
            ImageCarouselAdapter.OnItemClickListener {
            override fun onItemClick(data: String, position: Int) {
                onClick?.invoke(data, position)
            }

        })

        binding.imageCarouselRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(
                recyclerView: RecyclerView,
                newState: Int
            ) {
                super.onScrollStateChanged(recyclerView, newState)
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    val position =
                        (recyclerView.layoutManager as LinearLayoutManager)
                            .findFirstVisibleItemPosition()
                    onChangePosition?.invoke(position)
                    circlesProgressBarView?.setPosition(position)

                }
            }

        })


        val snapHelper: SnapHelper = PagerSnapHelper()
        if (binding.imageCarouselRecyclerView.onFlingListener == null)
            snapHelper.attachToRecyclerView(binding.imageCarouselRecyclerView)
    }

    fun onChangePosition(onChangePosition: (position: Int) -> Unit): ImageCarouselView {
        this.onChangePosition = onChangePosition
        return this
    }

    fun addProgressBar(view:ViewGroup): ImageCarouselView {
        circlesProgressBarView = CirclesProgressBarView( binding.root)
        circlesProgressBarView!!.setSize(imageUrls.size)
        return this

    }

    fun onClickItem(onClick: (data: String, position: Int) -> Unit): ImageCarouselView {
        this.onClick = onClick
        return this
    }

}