package com.hehebaba.practise.animate.widget

import android.graphics.*
import android.graphics.drawable.Drawable
import kotlin.math.min

class RoundDrawable : Drawable() {

    private var alpha = 0
    private val radius = 200F

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.RED
    }

    override fun draw(canvas: Canvas) {
        val width = bounds.width()
        val height = bounds.height()
        val radius = min(width, height) / 2.toFloat()
        canvas.drawCircle(width / 2F, height / 2F, radius, paint)
    }

    override fun setAlpha(alpha: Int) {
        this.alpha = alpha
    }

    override fun getAlpha(): Int {
        return alpha
    }

    override fun getOpacity(): Int {
        return PixelFormat.OPAQUE;
    }

    override fun setColorFilter(colorFilter: ColorFilter?) {

    }
}