package com.github.dant3.eshop.ui

import android.graphics.Rect
import android.support.v4.view.ViewCompat
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.github.dant3.eshop.ui.util.Dimension

class ItemPaddingDecoration(private val itemSpacing: Dimension,
                            private val leftPadding: Dimension = Dimension.Zero,
                            private val rightPadding: Dimension = Dimension.Zero,
                            private val topPadding: Dimension = Dimension.Zero,
                            private val bottomPadding: Dimension = Dimension.Zero,
                            val spacingMode: ItemPaddingDecoration.SpacingMode? = null) : RecyclerView.ItemDecoration() {
    constructor(itemSpacing: Dimension,
                padding: Dimension = Dimension.Zero,
                spacingMode: ItemPaddingDecoration.SpacingMode? = null)
            : this(itemSpacing, padding, padding, padding, padding, spacingMode)

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        val position = parent.getChildViewHolder(view).adapterPosition
        val itemCount = state.itemCount
        setSpacingForDirection(outRect, parent, position, itemCount)
    }

    private fun setSpacingForDirection(outRect: Rect, parent: RecyclerView, position: Int, itemCount: Int) {
        // Resolve display mode automatically
        val layoutManager = parent.layoutManager
        val spacingMode = this.spacingMode ?: resolveDisplayMode(layoutManager)

        val itemSpacing = itemSpacing.toPx(parent.context) / 2 // applied on two items equally
        val topPadding = topPadding.toPx(parent.context)
        val bottomPadding = bottomPadding.toPx(parent.context)
        val leftPadding = leftPadding.toPx(parent.context)
        val rightPadding = rightPadding.toPx(parent.context)

        when (spacingMode) {
            SpacingMode.Horizontal -> {
                outRect.left = if (position == 0) leftPadding else itemSpacing
                outRect.right = if (position == itemCount - 1) rightPadding else itemSpacing
                outRect.top = topPadding
                outRect.bottom = bottomPadding
            }
            SpacingMode.Vertical -> {
                outRect.left = leftPadding
                outRect.right = rightPadding
                outRect.top = if (position == 0) topPadding else itemSpacing
                outRect.bottom = if (position == itemCount - 1) bottomPadding else itemSpacing
            }
            SpacingMode.Grid -> if (layoutManager is GridLayoutManager) {
                val cols = layoutManager.spanCount
                val rows = itemCount / cols

                outRect.left = if (position % cols == 0) leftPadding else itemSpacing
                outRect.right = if (position % cols == cols - 1) rightPadding else itemSpacing
                outRect.top = if (position / cols == 0) topPadding else itemSpacing
                outRect.bottom = if (position / cols == rows - 1) bottomPadding else itemSpacing
            }
        }
        val isRtl = layoutManager.isRtl()
        val inversedVertically = (layoutManager as? LinearLayoutManager)?.reverseLayout ?: false

        outRect.inverse(inversedVertically, isRtl)
    }

    private fun Rect.inverse(vertically: Boolean, horizontally: Boolean) {
        if (vertically) {
            val top = this.top
            this.top = this.bottom
            this.bottom = top
        }
        if (horizontally) {
            val left = this.left
            this.left = this.right
            this.right = left
        }
    }

    enum class SpacingMode {
        Horizontal,
        Vertical,
        Grid
    }


    private fun resolveDisplayMode(layoutManager: RecyclerView.LayoutManager): SpacingMode = when {
        layoutManager is GridLayoutManager -> SpacingMode.Grid
        layoutManager.canScrollHorizontally() -> SpacingMode.Horizontal
        else -> SpacingMode.Vertical
    }

    private fun RecyclerView.LayoutManager.isRtl(): Boolean =
            layoutDirection == ViewCompat.LAYOUT_DIRECTION_RTL
}