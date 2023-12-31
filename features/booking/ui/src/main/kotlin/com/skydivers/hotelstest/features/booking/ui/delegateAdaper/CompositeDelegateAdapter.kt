package com.skydivers.hotelstest.features.booking.ui.delegateAdaper

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder



/**
 * @author dumchev on 03.11.17.
 */
open class CompositeDelegateAdapter(vararg adapters: DelegateAdapter) :
    RecyclerView.Adapter<ViewHolder>() {

    //  Contract is: adapters position is used as ViewType.
    protected open var adapterState = AdaptersState(adapters.toList())

    override fun getItemViewType(itemPosition: Int): Int =
        adapterState.getAdapterPosition(itemPosition)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        adapterState.getAdapter(viewType).onCreateViewHolder(parent, viewType)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        adapterState.getAdapter(getItemViewType(position))
            .onBindViewHolder(holder, adapterState.data, position)

    override fun onViewRecycled(holder: ViewHolder) =
        adapterState.getAdapter(holder.itemViewType).onRecycled(holder)

    override fun getItemId(position: Int): Long {
        return super.getItemId(position)
    }


    fun findAdapters(adapter: DelegateAdapter): List<DelegateAdapter> {

        val adaptersList = ArrayList<DelegateAdapter>()


        for (i in 0 until adapterState.data.size) {

            if (adapterState.getAdapterByItemPosition(i)::class.simpleName == adapter::class.simpleName) {
                adaptersList.add(adapterState.getAdapterByItemPosition(i))
            }
        }
        return adaptersList
    }


    open fun swapData(data: List<Any>) {

        val newAdapterState = adapterState.copy(data = data)
        val diffCallback = DiffUtilCallback(adapterState, newAdapterState)
        val diffResult = DiffUtil.calculateDiff(diffCallback)

        adapterState = newAdapterState
        diffResult.dispatchUpdatesTo(this)

    }

    override fun onViewAttachedToWindow(holder: ViewHolder) {
        adapterState.getAdapter(holder.itemViewType).onAttachedToWindow(holder)
    }

    override fun onViewDetachedFromWindow(holder: ViewHolder) {
        adapterState.getAdapter(holder.itemViewType).onDetachedFromWindow(holder)
    }

    override fun getItemCount(): Int = adapterState.data.size

}