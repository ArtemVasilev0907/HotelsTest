package com.skydivers.hotelstest.features.rooms.presentation.fragment.views.imageCarousel

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SnapHelper
import com.skydivers.hotelstest.features.rooms.ui.R


class ImageCarouselView constructor(
    private val view: View,
    private val imageUrls: List<String>
) {



    private var onChangePosition: ((position: Int) -> Unit)? = null
    private var onClick: ((data: String, position: Int) -> Unit)? = null
    private var circlesProgressBarView: CirclesProgressBarView? = null

    private val imageCarouselRecycler =
        view.findViewById<RecyclerView>(R.id.imageCarouselRecyclerView)


    fun show() {

        val imageCarouselAdapter = ImageCarouselAdapter(imageUrls)
        imageCarouselRecycler.adapter = imageCarouselAdapter

        imageCarouselAdapter.setOnItemClickListener(object :
            ImageCarouselAdapter.OnItemClickListener {
            override fun onItemClick(data: String, position: Int) {
                onClick?.invoke(data, position)
            }

        })

        imageCarouselRecycler.addOnScrollListener(object : RecyclerView.OnScrollListener() {
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
        if (imageCarouselRecycler.onFlingListener == null)
            snapHelper.attachToRecyclerView(imageCarouselRecycler)
    }

    fun onChangePosition(onChangePosition: (position: Int) -> Unit): ImageCarouselView {
        this.onChangePosition = onChangePosition
        return this
    }

    fun addProgressBar(view:ViewGroup): ImageCarouselView {
        circlesProgressBarView = CirclesProgressBarView( view)
        circlesProgressBarView!!.setSize(imageUrls.size)
        return this

    }

    fun onClickItem(onClick: (data: String, position: Int) -> Unit): ImageCarouselView {
        this.onClick = onClick
        return this
    }

}