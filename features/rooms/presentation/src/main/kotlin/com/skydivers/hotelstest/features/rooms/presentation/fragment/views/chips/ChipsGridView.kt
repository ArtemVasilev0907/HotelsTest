package com.skydivers.hotelstest.features.rooms.presentation.fragment.views.chips

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.skydivers.hotelstest.features.rooms.presentation.fragment.views.chips.ChipsAdapter
import com.skydivers.hotelstest.features.rooms.ui.R


class ChipsGridView constructor(
    view: View,
    private val chips: List<String>
) {

    private val chipsRecycler = view.findViewById<RecyclerView>(R.id.rvChipsGrid)
    fun init() {
        chipsRecycler.adapter = ChipsAdapter(chips)

    }


}