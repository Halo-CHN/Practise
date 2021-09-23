package com.hehebaba.scalable_image_view.view

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.widget.FrameLayout

class OnTouchEventViewGroup(context: Context, attrs: AttributeSet?) : FrameLayout(context, attrs) {
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        Log.d(this.javaClass.simpleName, "onTouchEvent ${event?.actionMasked}")
        return super.onTouchEvent(event)
    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        Log.d(this.javaClass.simpleName, "dispatchTouchEvent ${ev?.actionMasked}")
        return super.dispatchTouchEvent(ev)
    }

    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        Log.d(this.javaClass.simpleName, "onInterceptTouchEvent ${ev?.actionMasked}")
        return super.onInterceptTouchEvent(ev)
    }
}