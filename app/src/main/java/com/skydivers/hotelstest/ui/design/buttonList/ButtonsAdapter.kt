package com.skydivers.hotelstest.ui.design.buttonList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.skydivers.hotelstest.R


class ButtonsAdapter(
    private val buttons: List<ButtonModel>
) : RecyclerView.Adapter<ButtonsAdapter.ButtonsViewHolder>() {


    class ButtonsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val layout: LinearLayout = itemView.findViewById(R.id.buttonTextLayout) as LinearLayout
        val title: TextView = itemView.findViewById(R.id.buttonTitle)
        val description: TextView = itemView.findViewById(R.id.buttonDescription)
        val leftImage: ImageView = itemView.findViewById(R.id.leftImage)
        val rightImage: ImageView = itemView.findViewById(R.id.rightImage)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ButtonsViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.button_item, parent, false)
        )


    override fun onBindViewHolder(holder: ButtonsViewHolder, position: Int) {


        if (position == 0) {
         holder.layout.setBackgroundResource(0)
        }

        holder.title.text = buttons[position].title
        holder.description.text = buttons[position].description
        buttons[position].leftImage?.let { holder.leftImage.setImageResource(it)
        holder.rightImage.setImageResource( buttons[position].rightImage)
        }

    }

    override fun getItemCount() = buttons.size


}