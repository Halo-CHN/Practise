package com.hehebaba.touch

import android.content.Context
import android.util.AttributeSet
import android.view.ViewGroup

class TouchView constructor(context: Context, attrs: AttributeSet?) : ViewGroup(context, attrs) {

    override fun shouldDelayChildPressedState(): Boolean {
        return false
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {

    }
}