package com.hehebaba.practise.animate.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.hehebaba.practise.animate.Utils

class PointView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.GRAY
    }

    private var radius = Utils.dp2px(20)
        set(value) {
            field = value
            invalidate()
        }

    var offsetX = Utils.dp2px(0)
        set(value) {
            field = value
            invalidate()
        }

    var offsetY = Utils.dp2px(0)
        set(value) {
            field = value
            invalidate()
        }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.run {
            drawCircle(offsetX, offsetY, radius, paint)
        }
    }
}