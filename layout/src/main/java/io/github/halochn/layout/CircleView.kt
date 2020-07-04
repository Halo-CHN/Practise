package io.github.halochn.layout

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

class CircleView(context: Context?, attributeSet: AttributeSet?) : View(context, attributeSet) {

    private val REDIUS = 200F
    private val PADDING = 50F
    private val mPaint = Paint(Paint.ANTI_ALIAS_FLAG)

    constructor(context: Context?) : this(context, null)

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        val circleDiameter = (REDIUS + PADDING) * 2

        val width = resolveSize(circleDiameter.toInt(), widthMeasureSpec)
        val height = resolveSize(circleDiameter.toInt(), heightMeasureSpec)
        setMeasuredDimension(
            width,
            height
        )
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.drawCircle(REDIUS + PADDING, REDIUS + PADDING, REDIUS, mPaint)
    }
}