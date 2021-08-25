package com.hehebaba.practise.animate.widget

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.hehebaba.practise.animate.R
import com.hehebaba.practise.animate.Utils

class CameraRotateView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    private val IMAGE_WIDTH = Utils.dp2px(200)
    private var bitmap: Bitmap
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val camera: Camera = Camera()
    private var offsetX = 0f
    private var offsetY = 0f
    var cameraRotateDegree = 30f
    var canvasRotateDegree = 30f
    var rectF = RectF()

    init {
        bitmap = getBitmap(IMAGE_WIDTH.toInt())
        camera.setLocation(0f, 0f, -6 * resources.displayMetrics.density)
        camera.rotateX(cameraRotateDegree)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        offsetX = width / 2f - bitmap.width / 2f
        offsetY = height / 2f - bitmap.width / 2f
    }

    private fun getBitmap(imageWidth: Int): Bitmap {
        val options = BitmapFactory.Options()
        options.inJustDecodeBounds = true
        BitmapFactory.decodeResource(resources, R.drawable.sellcar_displace_top_bg_2, options)
        val width = options.outWidth
        options.inJustDecodeBounds = false
        options.inDensity = width
        options.inTargetDensity = imageWidth
        return BitmapFactory.decodeResource(
            resources,
            R.drawable.sellcar_displace_top_bg_2,
            options
        )
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.run {
            save()
            translate(offsetX + bitmap.width / 2f, offsetY + bitmap.height / 2f)
            rotate(-canvasRotateDegree)
            rectF.set(-bitmap.width.toFloat(), -bitmap.height.toFloat(), bitmap.width.toFloat(), 0f)
            clipRect(rectF)
            rotate(canvasRotateDegree)
            translate(-offsetX - bitmap.width / 2f, -offsetY - bitmap.height / 2f)
            drawBitmap(bitmap, offsetX, offsetY, paint)
            restore()

            save()
            translate(offsetX + bitmap.width / 2f, offsetY + bitmap.height / 2f)
            rotate(-canvasRotateDegree)
            camera.save()
            camera.applyToCanvas(this)
            camera.restore()
            rectF.set(-bitmap.width.toFloat(), 0f, bitmap.width.toFloat(), bitmap.height.toFloat())
            clipRect(rectF)
            rotate(canvasRotateDegree)
            translate(-offsetX - bitmap.width / 2f, -offsetY - bitmap.height / 2f)
            drawBitmap(bitmap, offsetX, offsetY, paint)
            restore()
        }
    }
}