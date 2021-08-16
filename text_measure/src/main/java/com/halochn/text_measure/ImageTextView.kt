package com.halochn.text_measure

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.view.View

class ImageTextView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    private val IMAGE_WIDTH = Utils.dp2px(150)
    private val IMAGE_OFFSET = Utils.dp2px(120)
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val textPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private var bitmap: Bitmap
    private var textLength: Int
    private var text: String = resources.getString(R.string.text_about).apply {
        textLength = length
    }
    private var textOffset: Float
    private var fontMetrics = Paint.FontMetrics()
    private val measuredWidth = FloatArray(1)

    val imageTop = IMAGE_OFFSET
    var imageBottom: Float
    val textHeight: Float

    init {
        bitmap = getBitmap(IMAGE_WIDTH.toInt())
        imageBottom = imageTop + bitmap.height
        textPaint.textSize = Utils.dp2px(16)
        textOffset = textPaint.fontSpacing
        textPaint.getFontMetrics(fontMetrics)
        textPaint.typeface = Typeface.createFromAsset(context?.assets, "Quicksand-Regular.ttf")
        textHeight = fontMetrics.top - fontMetrics.bottom
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        var offsetY = textOffset
        var start = 0
        val measureWidth = MeasureSpec.getSize(widthMeasureSpec)
        var lineCount = 0
        while (start < textLength) {
            val breakText =
                if ((offsetY > imageTop && offsetY < imageBottom) || (offsetY + textHeight > imageTop && offsetY + textHeight < imageBottom)) {
                    textPaint.breakText(
                        text,
                        start,
                        textLength,
                        true,
                        measureWidth.toFloat() - IMAGE_WIDTH,
                        measuredWidth
                    )
                } else {
                    textPaint.breakText(
                        text,
                        start,
                        textLength,
                        true,
                        measureWidth.toFloat(),
                        measuredWidth
                    )
                }
            offsetY += textOffset
            start += breakText
            lineCount++
        }
        Log.d(
            "ImageTextView",
            "widthMeasureSpec = ${MeasureSpec.getSize(widthMeasureSpec)} height = ${offsetY.toInt()}"
        )
        Log.d(
            "ImageTextView",
            "widthMeasureSpec = ${MeasureSpec.getSize(widthMeasureSpec)} heightMeasureSpec = ${
                MeasureSpec.getSize(
                    heightMeasureSpec
                )
            }"
        )
        Log.d(
            "ImageTextView",
            "lineCount = $lineCount textHeight = $textHeight textOffset = $textOffset"
        )
        super.onMeasure(
            widthMeasureSpec,
            MeasureSpec.makeMeasureSpec(
                offsetY.toInt(),
                MeasureSpec.AT_MOST
            )
        )
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        Log.d(
            "ImageTextView",
            "width = $width height = $height}"
        )
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.run {
            drawBitmap(bitmap, width - IMAGE_WIDTH, IMAGE_OFFSET, paint)
            var start = 0
            var offsetY = textOffset
            while (start < textLength) {
                val breakText =
                    if ((offsetY > imageTop && offsetY < imageBottom) || (offsetY + textHeight > imageTop && offsetY + textHeight < imageBottom)) {
                        textPaint.breakText(
                            text,
                            start,
                            textLength,
                            true,
                            width.toFloat() - IMAGE_WIDTH,
                            measuredWidth
                        )
                    } else {
                        textPaint.breakText(
                            text,
                            start,
                            textLength,
                            true,
                            width.toFloat(),
                            measuredWidth
                        )
                    }
                drawText(text, start, start + breakText, 0f, offsetY, textPaint)
                offsetY += textOffset
                start += breakText
            }
        }
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
}