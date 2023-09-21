package com.skydivers.hotelstest.ui.design.chips

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.skydivers.hotelstest.R


class ChipsGridView constructor(
    view: View,
    private val chips: List<String>
) {

    private val chipsRecycler = view.findViewById<RecyclerView>(R.id.rvChipsGrid)
    fun init() {
        chipsRecycler.adapter = ChipsAdapter(chips)

    }


}