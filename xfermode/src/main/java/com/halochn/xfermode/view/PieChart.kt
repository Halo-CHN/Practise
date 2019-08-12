package com.halochn.xfermode.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import com.halochn.xfermode.Utils
import kotlin.math.cos
import kotlin.math.sin

class PieChart(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    private val RADIUS = Utils.dp2px(150)
    private val PULL_LENGTH = Utils.dp2px(20)
    private val Angles = floatArrayOf(60f, 80f, 120f, 100f)
    private val Colors = intArrayOf(
        Color.parseColor("#448AFF"),
        Color.parseColor("#D81B60"),
        Color.parseColor("#43A047"),
        Color.parseColor("#FDD835")
    )
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val PULL_INDEX = 1
    private lateinit var rectF: RectF

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        rectF = RectF(
            width / 2 - RADIUS,
            height / 2 - RADIUS,
            width / 2 + RADIUS,
            height / 2 + RADIUS
        )
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        var currentAngle = 0f
        for (i in 0 until Angles.size) {
            paint.color = Colors[i]
            if (i == PULL_INDEX) {
                canvas?.save()
                canvas?.translate(
                    (cos(Math.toRadians((currentAngle + Angles[i] / 2).toDouble())) * PULL_LENGTH).toFloat(),
                    (sin(Math.toRadians((currentAngle + Angles[i] / 2).toDouble())) * PULL_LENGTH).toFloat()
                )
            }
            canvas?.drawArc(
                rectF
                , currentAngle, Angles[i], true, paint
            )
            if (i == PULL_INDEX) {
                canvas?.restore()
            }
            currentAngle += Angles[i]
        }
    }
}