package com.hehebaba.scalable_image_view.view

import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import android.widget.OverScroller
import androidx.core.view.GestureDetectorCompat
import androidx.core.view.ViewCompat
import com.hehebaba.mylibrary.utils.Utils
import com.hehebaba.scalable_image_view.R

class ScalableImageView : View, GestureDetector.OnGestureListener,
    GestureDetector.OnDoubleTapListener, Runnable {

    private val IMAGE_WIDTH: Int = Utils.dp2Pixel(300F).toInt()
    private var bitmap: Bitmap
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val OVER_SCALE_FACTOR = 1.5F

    private var smallScale: Float = 0F
    private var bigScale: Float = 0F

    private var originalOffsetX: Float = 0F
    private var originalOffsetY: Float = 0F
    private var offsetX: Float = 0F
    private var offsetY: Float = 0F
    private var big: Boolean = false

    private var maxOffsetX = 0F
    private var maxOffsetY = 0F

    private val overScroller: OverScroller

    var scaleFraction: Float = 0F
        set(value) {
            field = value
            //当缩小动画完成后 设置偏移为0
            if (!big && field == 0F) {
                offsetX = 0F
                offsetY = 0F
            }
            invalidate()
        }

    private var gestureDetector: GestureDetectorCompat
    private val scaleAnimator: ObjectAnimator by lazy {
        ObjectAnimator.ofFloat(this, "scaleFraction", 0F, 1F)
    }

    constructor(context: Context?, attrs: AttributeSet) : super(context, attrs) {
        bitmap = Utils.getBitmap(resources, R.drawable.film_cover, IMAGE_WIDTH)
        gestureDetector = GestureDetectorCompat(context, this)
        overScroller = OverScroller(context)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        originalOffsetX = (width - bitmap.width) / 2F
        originalOffsetY = (height - bitmap.height) / 2F

        if (bitmap.width.toFloat() / bitmap.height > width.toFloat() / height.toFloat()) {
            smallScale = width / bitmap.width.toFloat()
            bigScale = height / bitmap.height.toFloat() * OVER_SCALE_FACTOR
        } else {
            smallScale = height / bitmap.height.toFloat()
            bigScale = width / bitmap.width.toFloat() * OVER_SCALE_FACTOR
        }

        maxOffsetX = (bitmap.width * bigScale - width) / 2F
        maxOffsetY = (bitmap.height * bigScale - height) / 2F
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.translate(offsetX * scaleFraction, offsetY * scaleFraction)
        val scale = smallScale + (bigScale - smallScale) * scaleFraction
        canvas?.scale(scale, scale, width / 2F, height / 2F)
        canvas?.drawBitmap(bitmap, originalOffsetX, originalOffsetY, paint)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        return gestureDetector.onTouchEvent(event)
    }

    override fun onShowPress(e: MotionEvent?) {

    }

    override fun onSingleTapUp(e: MotionEvent?): Boolean {
        return false
    }

    override fun onDown(e: MotionEvent?): Boolean {
        if (!overScroller.isFinished) {
            overScroller.abortAnimation()
        }
        return true
    }

    override fun onFling(
        e1: MotionEvent?,
        e2: MotionEvent?,
        velocityX: Float,
        velocityY: Float
    ): Boolean {
        if (big) {
            overScroller.fling(
                offsetX.toInt(),
                offsetY.toInt(),
                velocityX.toInt(),
                velocityY.toInt(),
                (-maxOffsetX).toInt(),
                maxOffsetX.toInt(),
                (-maxOffsetY).toInt(),
                maxOffsetY.toInt(), Utils.dp2Pixel(50F).toInt(), Utils.dp2Pixel(50F).toInt()
            )
            ViewCompat.postOnAnimation(this, this)
        }
        return false
    }

    override fun onScroll(
        e1: MotionEvent?,
        e2: MotionEvent?,
        distanceX: Float,
        distanceY: Float
    ): Boolean {
        if (big) {
            offsetX -= distanceX
            offsetY -= distanceY
            fixOffsets()
            invalidate()
        }
        return false
    }

    private fun fixOffsets() {
        offsetX = Math.max(Math.min(offsetX, maxOffsetX), -maxOffsetX)
        offsetY = Math.max(Math.min(offsetY, maxOffsetY), -maxOffsetY)
    }

    override fun onLongPress(e: MotionEvent?) {

    }

    override fun onDoubleTap(e: MotionEvent?): Boolean {
        big = !big
        if (big) {
            e?.let {
                offsetX = (e.x - width / 2F) * (1 - bigScale / smallScale)
                offsetY = (e.y - height / 2F) * (1 - bigScale / smallScale)
                fixOffsets()
            }
            scaleAnimator.start()
        } else {
            scaleAnimator.reverse()
        }
        return true
    }

    private fun refresh() {
        if (overScroller.computeScrollOffset()) {
            offsetX = overScroller.currX.toFloat()
            offsetY = overScroller.currY.toFloat()
            invalidate()
            ViewCompat.postOnAnimation(this, this)
        }
    }

    override fun onDoubleTapEvent(e: MotionEvent?): Boolean {
        return false
    }

    override fun onSingleTapConfirmed(e: MotionEvent?): Boolean {
        return false
    }

    override fun run() {
        refresh()
    }
}