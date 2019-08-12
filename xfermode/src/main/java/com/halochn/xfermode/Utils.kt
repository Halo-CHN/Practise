package com.halochn.xfermode

import android.content.res.Resources
import android.util.TypedValue

object Utils {
    fun dp2px(value: Int): Float {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            value.toFloat(),
            Resources.getSystem().displayMetrics
        )
    }
}