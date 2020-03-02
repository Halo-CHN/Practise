package com.halochn.hencoderpractise

import android.content.Context
import android.util.TypedValue

class StatusBarUtil {
    companion object {
        fun getStatusBarHeight(context: Context): Int {
            return context.applicationContext.resources.getIdentifier(
                "status_bar_height",
                "dimen",
                "android"
            ).let {
                when {
                    it > 0 -> context.resources.getDimensionPixelSize(it)
                    else -> TypedValue.applyDimension(
                        TypedValue.COMPLEX_UNIT_DIP, 24.toFloat(),
                        context.applicationContext.resources.displayMetrics
                    ).toInt()
                }
            }
        }
    }
}