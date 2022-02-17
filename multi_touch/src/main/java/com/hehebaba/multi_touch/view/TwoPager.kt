package com.hehebaba.multi_touch.view

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.ViewConfiguration
import android.view.ViewGroup
import kotlin.math.absoluteValue

class TwoPager(context: Context?, attrs: AttributeSet?) : ViewGroup(context, attrs) {

    private var downX = 0f
    private var downScrollX = 0
    private var scrolling = false
    private var viewConfiguration: ViewConfiguration = ViewConfiguration.get(context)

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        measureChildren(widthMeasureSpec, heightMeasureSpec)
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        var childLeft = 0
        var childRight = width
        var childTop = 0
        var childBottom = height
        for (i in 0 until childCount) {
            getChildAt(i).layout(childLeft, childTop, childRight, childBottom)
            childLeft += width
            childRight += width
        }
    }

    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        var result = false
        ev?.run {
            when (actionMasked) {
                MotionEvent.ACTION_DOWN -> {
                    downX = x
                    scrolling = false
                    downScrollX = scrollX
                }
                MotionEvent.ACTION_MOVE -> {
                    val dx = downX - x
                    if (dx.absoluteValue > viewConfiguration.scaledPagingTouchSlop) {
                        scrolling = true
                        parent.requestDisallowInterceptTouchEvent(true)
                        result = true
                    }
                }
            }
        }
        return result
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        event?.run {
            when (actionMasked) {
                MotionEvent.ACTION_DOWN -> {
                    downX = x
                    scrolling = false
                    downScrollX = scrollX
                }
                MotionEvent.ACTION_MOVE -> {
                    val dx = Math.min(Math.max(0f, downX - x + downScrollX), measuredWidth * 1f)
                    scrollTo(dx.toInt(), 0)
                }
            }
        }
        return true
    }
}