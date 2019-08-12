package com.halochn.xfermode.view

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.halochn.xfermode.Utils

class DashBoard(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    private val RADIUS = Utils.dp2px(150)
    private val ANGLE = 120
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val dash = Path()
    private var path: Path
    private lateinit var dashPathEffect: PathDashPathEffect
    private lateinit var pathMeasure: PathMeasure

    init {
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = Utils.dp2px(3)
        dash.addRect(0F, 0F, Utils.dp2px(2), Utils.dp2px(10), Path.Direction.CW)
        path = Path()
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        path.addArc(
            width / 2 - RADIUS,
            height / 2 - RADIUS,
            width / 2 + RADIUS,
            height / 2 + RADIUS,
            (90 + ANGLE / 2).toFloat(), (360 - ANGLE).toFloat()
        )
        pathMeasure = PathMeasure(path, false)
        dashPathEffect = PathDashPathEffect(
            dash,
            (pathMeasure.length - Utils.dp2px(2)) / 20,
            0F,
            PathDashPathEffect.Style.ROTATE
        )
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        //画原图形
        canvas?.drawArc(
            width / 2 - RADIUS,
            height / 2 - RADIUS,
            width / 2 + RADIUS,
            height / 2 + RADIUS,
            (90 + ANGLE / 2).toFloat(), (360 - ANGLE).toFloat(), false, paint
        )

        //画刻度
        paint.pathEffect = dashPathEffect
        canvas?.drawArc(
            width / 2 - RADIUS,
            height / 2 - RADIUS,
            width / 2 + RADIUS,
            height / 2 + RADIUS,
            (90 + ANGLE / 2).toFloat(), (360 - ANGLE).toFloat(), false, paint
        )
        paint.pathEffect = null

        //画指针

    }
}