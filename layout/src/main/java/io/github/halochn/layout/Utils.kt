package io.github.halochn.layout

import android.content.res.Resources
import android.util.TypedValue

class Utils {
    companion object {
        fun dpToPixel(dp: Float): Float {
            return TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                dp,
                Resources.getSystem().displayMetrics
            )
        }
    }
}