package com.github.dant3.eshop.ui.search

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.github.dant3.eshop.model.ItemSummary


class ItemsAdapter : RecyclerView.Adapter<ItemBriefCardViewHolder>() {
    var items: List<ItemSummary> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    private var itemClicked = MutableLiveData<ItemSummary>()

    val onItemClicked: LiveData<ItemSummary>
        get() = itemClicked

    override fun onBindViewHolder(holder: ItemBriefCardViewHolder, position: Int) {
        holder.bind(getItem(position), itemClicked)
    }

    private fun getItem(position: Int): ItemSummary = items[position]
    override fun getItemCount(): Int = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ItemBriefCardViewHolder(parent)
}