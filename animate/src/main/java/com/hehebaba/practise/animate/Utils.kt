package com.hehebaba.practise.animate

import android.app.Activity
import android.app.Service
import android.content.Context
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Point
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
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

    fun getDisplay(context: Context?): Display? {
        val windowManager: WindowManager = if (context is Activity) {
            context.windowManager
        } else {
            context?.getSystemService(Service.WINDOW_SERVICE) as WindowManager
        }
        return windowManager.defaultDisplay
    }

    fun drawableToBitmap(drawable: Drawable): Bitmap {
        if (drawable is BitmapDrawable) {
            if (null != drawable.bitmap) {
                return drawable.bitmap
            }
        }
        val bitmap = if (drawable.intrinsicWidth <= 0 || drawable.intrinsicHeight <= 0) {
            Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888)
        } else {
            Bitmap.createBitmap(
                drawable.intrinsicWidth,
                drawable.intrinsicHeight,
                Bitmap.Config.ARGB_8888
            )
        }
        Canvas(bitmap).run {
            drawable.setBounds(0, 0, width, height)
            drawable.draw(this)
        }
        return bitmap
    }

    fun bitmapToDrawable(bitmap: Bitmap): Drawable {
        return BitmapDrawable(Resources.getSystem(), bitmap)
    }
}