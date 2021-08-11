package com.halochn.xfermode.view

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.halochn.xfermode.R
import com.halochn.xfermode.Utils
import kotlin.math.min

internal class DrawBitmap(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private lateinit var bitmap: Bitmap
    private val RADIUS = Utils.dp2px(100)
    private val rectF = RectF()
    private val xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_OUT)
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        canvas.drawCircle(
            bitmap.width / 2f,
            bitmap.height / 2f,
            min(bitmap.width, bitmap.height) / 2f,
            paint
        )
        val count = canvas.saveLayer(rectF, paint)
        canvas.drawArc(
            rectF,
            0f, 180f,
            false,
            paint
        )
        paint.xfermode = xfermode
        canvas.drawBitmap(bitmap, 0f, 0f, paint)
        canvas.restoreToCount(count)
        paint.reset()
    }

    private fun getBitmap(width: Int): Bitmap {
        val options = BitmapFactory.Options()
        options.inJustDecodeBounds = true
        BitmapFactory.decodeResource(resources, R.drawable.sellcar_displace_top_bg_2, options)
        val outWidth = options.outWidth
        options.inJustDecodeBounds = false
        options.inDensity = outWidth
        options.inTargetDensity = width
        return BitmapFactory.decodeResource(
            resources,
            R.drawable.sellcar_displace_top_bg_2,
            options
        )
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        bitmap = getBitmap(width)
        rectF.apply {
            left = 0f
            top = 0f
            right = bitmap.width.toFloat()
            bottom = bitmap.height.toFloat()
        }
    }
}