package com.hehebaba.multi_touch.view

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

class TouchableView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        return true
    }
}