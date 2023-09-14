package com.skydivers.hotelstest.ui.design.buttonList

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.skydivers.hotelstest.R



class ButtonsView constructor(
    view: View
) {

    private val buttonsRecycler = view.findViewById<RecyclerView>(R.id.ButtonsRecyclerView)
    fun init() {
        val buttons: MutableList<ButtonModel> = mutableListOf(
            ButtonModel(R.drawable.emoji_happy,
            title = "Удобства",
            description = "Самое необходимое"),
            ButtonModel(R.drawable.tick_square,
                title = "Что включено",
                description = "Самое необходимое"),
            ButtonModel(R.drawable.close_square,
                title = "Что не включено",
                description = "Cамое необходимое")
        )


        buttonsRecycler.adapter = ButtonsAdapter(buttons)

    }


}