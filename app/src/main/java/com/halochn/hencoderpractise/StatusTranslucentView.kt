package com.halochn.hencoderpractise

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.View
import androidx.core.content.ContextCompat

class StatusTranslucentView(context: Context, attrs: AttributeSet?) : View(context, attrs) {
    init {
        setBackgroundColor(Color.argb(0, 0, 0, 0))
    }
}