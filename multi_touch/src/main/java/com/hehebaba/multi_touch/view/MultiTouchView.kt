package com.hehebaba.multi_touch.view

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import com.hehebaba.multi_touch.R
import com.hehebaba.mylibrary.utils.Utils

class MultiTouchView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val IMAGE_WIDTH = Utils.dp2Pixel(300F)
    private var bitmap: Bitmap =
        Utils.getBitmap(resources, R.drawable.touch_pic, IMAGE_WIDTH.toInt())

    private var downX = 0F
    private var downY = 0F

    private var offsetX = 0F
    private var offsetY = 0F

    private var originalOffsetX = 0F
    private var originalOffsetY = 0F

    private var currentOffsetX = 0F
    private var currentOffsetY = 0F

    init {

    }

    override fun onDraw(canvas: Canvas?) {
        canvas?.run {
            offsetX = originalOffsetX - (downX - currentOffsetX)
            offsetY = originalOffsetY - (downY - currentOffsetY)
            drawBitmap(bitmap, offsetX, offsetY, paint)
        }
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        event?.let { e ->
            var availableIndex = 0
            when (event.actionMasked) {
                MotionEvent.ACTION_DOWN -> {
                    originalOffsetX = offsetX
                    originalOffsetY = offsetY
                    downX = e.x
                    downY = e.y
                    return true
                }
                MotionEvent.ACTION_MOVE -> {
                    currentOffsetX = e.getX(availableIndex)
                    currentOffsetY = e.getY(availableIndex)
                    invalidate()
                }
                MotionEvent.ACTION_POINTER_DOWN -> {
                    val actionIndex = e.actionIndex
                    availableIndex = actionIndex
                }
                MotionEvent.ACTION_POINTER_UP -> {
                    val actionIndex = e.actionIndex
                    originalOffsetX = offsetX
                    originalOffsetY = offsetY
                }
                MotionEvent.ACTION_UP -> {

                }
            }
        }
        return super.onTouchEvent(event)
    }
}