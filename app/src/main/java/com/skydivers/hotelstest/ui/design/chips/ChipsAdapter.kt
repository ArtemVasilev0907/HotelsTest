package com.skydivers.hotelstest.ui.design.chips

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.skydivers.hotelstest.R



class ChipsAdapter(
    private val chips: List<String>
) : RecyclerView.Adapter<ChipsAdapter.ChipsViewHolder>() {


    class ChipsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val chipText: TextView = itemView.findViewById(R.id.chipText)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ChipsViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.chip_item, parent, false)
        )


    override fun onBindViewHolder(holder: ChipsViewHolder, position: Int) {
        holder.chipText.text = chips[position]
    }

    override fun getItemCount() = chips.size


}