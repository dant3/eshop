package com.github.dant3.eshop.ui.util

import android.support.annotation.LayoutRes
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup

open class LayoutBasedViewHolder(parent: ViewGroup, @LayoutRes layout: Int): RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).inflate(layout, parent, false)
)