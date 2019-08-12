package com.halochn.xfermode

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.View

class DashBoard(context: Context?, attrs: AttributeSet?) :
    View(context, attrs) {

    private val RADIUS = Utils.dp2px(100)
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val path = Path()

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        path.fillType = Path.FillType.EVEN_ODD
        path.addCircle((width / 2).toFloat(), (height / 2).toFloat(), RADIUS, Path.Direction.CW)
        path.addRect(
            (width / 2).toFloat() - RADIUS,
            (height / 2).toFloat(),
            (width / 2).toFloat() + RADIUS,
            (height / 2).toFloat() + RADIUS * 2, Path.Direction.CW
        )
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.drawPath(path,paint)
    }
}