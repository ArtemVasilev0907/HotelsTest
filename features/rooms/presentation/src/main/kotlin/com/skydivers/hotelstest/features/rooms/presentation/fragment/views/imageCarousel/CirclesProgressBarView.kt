package com.skydivers.hotelstest.features.rooms.presentation.fragment.views.imageCarousel


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.skydivers.hotelstest.features.rooms.ui.R


class CirclesProgressBarView(
    view:View
    )
{
    private var position = 0
    private val  circles:ViewGroup = view.findViewById<LinearLayout>(R.id.circlesPrograssBar)

    fun setSize(size:Int){
        circles.removeAllViews()
        for (i in 0 until size) {
            LayoutInflater.from(circles.context
            ).inflate(R.layout.circle_view, circles, true)

            val childAt: View = circles.getChildAt(i)
           // val layoutParams = childAt.layoutParams as LinearLayout.LayoutParams
            if (i == position) {
                childAt.setBackgroundResource(R.drawable.circle_active)
            } else {
                childAt.setBackgroundResource(R.drawable.circle_default)
            }
           // childAt.layoutParams = layoutParams
        }
    }


     fun setPosition(currentPosition: Int){
         var i = 0
         while (true) {
             val childIndex = i
             if (childIndex < circles.childCount) {
                 val childAt: View = circles.getChildAt(childIndex)
                 val layoutParams =
                     childAt.layoutParams as LinearLayout.LayoutParams
                 if (childIndex == currentPosition) {
                     childAt.setBackgroundResource(R.drawable.circle_active)

                 } else {
                     childAt.setBackgroundResource(R.drawable.circle_default)
                 }
                 childAt.layoutParams = layoutParams
                 i = childIndex + 1
             } else {
                 return
             }
         }
    }

}

