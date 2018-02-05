package com.github.dant3.eshop.ui.util

import android.annotation.TargetApi
import android.content.Context
import android.content.res.Configuration
import android.os.Build
import android.util.TypedValue
import java.util.*


fun Context.dpToPx(dp: Int): Int {
    val r = resources
    return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp.toFloat(), r.displayMetrics))
}


val Configuration.localeCompat: Locale
    get() {
        @SuppressWarnings("deprecation")
        fun Configuration.getSystemLocaleLegacy() = locale
        @TargetApi(Build.VERSION_CODES.N)
        fun Configuration.getSystemLocaleModern() = locales.get(0);

        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            getSystemLocaleModern()
        } else {
            getSystemLocaleLegacy()
        }
    }
