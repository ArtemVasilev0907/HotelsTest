package com.skydivers.hotelstest.core.theme.design.views.chips

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.skydivers.theme.databinding.ChipsGridLayoutBinding


class ChipsGridView constructor(
    view: View,
    chips: List<String>
) {

    init {
        val inflater = LayoutInflater.from(view.context)
        val binding: ChipsGridLayoutBinding =
            ChipsGridLayoutBinding.inflate(inflater, view as ViewGroup, false)
        view.addView(binding.root)
        binding.rvChipsGrid.adapter = ChipsAdapter(chips)

    }


}