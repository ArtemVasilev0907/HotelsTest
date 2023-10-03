package com.skydivers.hotelstest.core.theme.design.views

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import com.skydivers.hotelstest.core.common.UiState
import com.skydivers.theme.databinding.CornerInfoFrameLayoutBinding


class CornerFrameView (
    view: View,
    onReloadData: (() -> Unit)? = null
) {
    private var binding: CornerInfoFrameLayoutBinding

    init {
        val inflater = LayoutInflater.from(view.context)
        binding = CornerInfoFrameLayoutBinding.inflate(inflater, view as ViewGroup, false)
        view.addView(binding.root)
        binding.tvErrorLoadingMessage.setOnClickListener {
            onReloadData?.invoke()
        }
    }

    fun observeState(state:UiState<Any>){
        binding.progressBar.isVisible = state is UiState.Loading
        binding.tvErrorLoadingMessage.isVisible = state is UiState.Error

    }





    private fun CornerInfoFrameLayoutBinding.bind() {






    }


}


