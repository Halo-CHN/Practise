package com.hehebaba.multi_touch.view

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import com.hehebaba.multi_touch.R
import com.hehebaba.mylibrary.utils.Utils

/**
 * 合作型
 */
class MultiTouchView2(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

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

    init {

    }

    override fun onDraw(canvas: Canvas?) {
        canvas?.run {

            drawBitmap(bitmap, offsetX, offsetY, paint)
        }
    }

    var availablePointerId = 0

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        event?.let { e ->
            when (event.actionMasked) {
                MotionEvent.ACTION_DOWN -> {
                    availablePointerId = e.getPointerId(0)
                    refreshXY(e)
                    return true
                }
                MotionEvent.ACTION_MOVE -> {
                    val availablePointerIndex = e.findPointerIndex(availablePointerId)
                    offsetX = originalOffsetX - (downX - e.getX(availablePointerIndex))
                    offsetY = originalOffsetY - (downY - e.getY(availablePointerIndex))
                    invalidate()
                }
                MotionEvent.ACTION_POINTER_DOWN -> {
                    Log.d("ACTION_POINTER_DOWN", "e.actionIndex: ${e.actionIndex}")
                    availablePointerId = e.getPointerId(e.actionIndex)
                    refreshXY(e)
                }
                MotionEvent.ACTION_POINTER_UP -> {
                    Log.d("ACTION_POINTER_UP", "e.actionIndex: ${e.actionIndex}")
                    if (availablePointerId == e.getPointerId(e.actionIndex)) {
                        availablePointerId = if (e.actionIndex == e.pointerCount - 1) {
                            e.getPointerId(e.pointerCount - 2)
                        } else {
                            e.getPointerId(e.pointerCount - 1)
                        }
                        refreshXY(e)
                    }
                }
                MotionEvent.ACTION_UP -> {

                }
                else -> {

                }
            }
        }
        return super.onTouchEvent(event)
    }

    private fun refreshXY(e: MotionEvent) {
        originalOffsetX = offsetX
        originalOffsetY = offsetY
        val availablePointerIndex = e.findPointerIndex(availablePointerId)
        downX = e.getX(availablePointerIndex)
        downY = e.getY(availablePointerIndex)
    }
}