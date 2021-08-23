package com.hehebaba.practise.animate

import android.app.Activity
import android.app.Service
import android.content.Context
import android.content.res.Resources
import android.graphics.Point
import android.os.Build
import android.util.DisplayMetrics
import android.util.TypedValue
import android.view.Display
import android.view.WindowManager

object Utils {
    fun dp2px(value: Int): Float {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            value.toFloat(),
            Resources.getSystem().displayMetrics
        )
    }

    fun getScreenWidth(context: Context?): Int {
        return with(Point()) {
            getDisplay(context)?.getRealSize(this)
            this.x
        }
    }


    fun getScreenWidth1(context: Context?): Int {
        return with(DisplayMetrics()) {
            getDisplay(context)?.getRealMetrics(this)
            this.widthPixels
        }
    }

    private fun getDisplay(context: Context?): Display? {
        val windowManager: WindowManager = if (context is Activity) {
            context.windowManager
        } else {
            context?.getSystemService(Service.WINDOW_SERVICE) as WindowManager
        }
        return windowManager.defaultDisplay
    }
}