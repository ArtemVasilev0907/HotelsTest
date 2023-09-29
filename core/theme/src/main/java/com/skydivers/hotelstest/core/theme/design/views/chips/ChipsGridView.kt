package com.skydivers.hotelstest.core.theme.design.views.chips

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.skydivers.theme.R


class ChipsGridView constructor(
    view: View,
    private val chips: List<String>
) {

    private val chipsRecycler = view.findViewById<RecyclerView>(R.id.rvChipsGrid)
    fun init() {
        chipsRecycler.adapter = ChipsAdapter(chips)

    }


}