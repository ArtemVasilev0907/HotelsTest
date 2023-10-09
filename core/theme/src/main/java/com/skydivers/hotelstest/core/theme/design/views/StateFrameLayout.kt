package com.skydivers.hotelstest.core.theme.design.views

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.core.view.children
import androidx.core.view.isVisible
import com.skydivers.hotelstest.core.common.UiState
import com.skydivers.theme.databinding.CornerInfoFrameLayoutBinding


class StateFrameLayout@JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0
): FrameLayout(context, attrs, defStyleAttr, defStyleRes) {
    var state: UiState<Any> = UiState.Loading
        set(value) {
            field = value
            notifyUpdates()
        }
    var onReload:(()->Unit)?=null

    private val binding: CornerInfoFrameLayoutBinding
    init {


        val inflater = LayoutInflater.from(context)
        binding = CornerInfoFrameLayoutBinding.inflate(inflater, this, false)
        addView(binding.root)


        binding.progressBar.isVisible = true
        binding.tvErrorLoadingMessage.isVisible = true
        binding.tvErrorLoadingMessage.setOnClickListener {

            onReload?.invoke()
        }

    }

    private fun notifyUpdates(){
        binding.progressBar.isVisible = state is UiState.Loading
        binding.tvErrorLoadingMessage.isVisible = state is UiState.Error

        Log.d(this::class.simpleName, state::class.simpleName.orEmpty())
        children.forEach {
            if (it != binding.root) {
                it.isVisible = state is UiState.Success
            }
        }
    }

}