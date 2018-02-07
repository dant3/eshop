package com.github.dant3.eshop.ui.util

import android.content.Context
import android.util.TypedValue

class Dimension(val value: Int, val unit: DimensionUnit) {
    fun toDp(context: Context): Int = toDpPrecisely(context).toInt()

    fun toDpPrecisely(context: Context): Float = when (unit) {
        DimensionUnit.px -> value / context.resources.displayMetrics.density
        else -> value.toFloat()
    }

    fun toPx(context: Context): Int = toPxPrecisely(context).toInt()

    fun toPxPrecisely(context: Context): Float =
            TypedValue.applyDimension(unit.asAndroid, value.toFloat(), context.resources.displayMetrics)

    operator fun times(multiplier: Int) = Dimension(value * multiplier, unit)
    operator fun div(divider: Int) = Dimension(value / divider, unit)

    companion object {
        val Zero = Dimension(0, DimensionUnit.px)
    }
}

enum class DimensionUnit(val asAndroid: Int) {
    dp(TypedValue.COMPLEX_UNIT_DIP),
    sp(TypedValue.COMPLEX_UNIT_SP),
    px(TypedValue.COMPLEX_UNIT_PX)
}

val Int.dp: Dimension
    get() = Dimension(this, DimensionUnit.dp)
val Int.px: Dimension
    get() = Dimension(this, DimensionUnit.px)
val Int.sp: Dimension
    get() = Dimension(this, DimensionUnit.sp)