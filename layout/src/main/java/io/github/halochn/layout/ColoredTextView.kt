package io.github.halochn.layout

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import java.util.*


class ColoredTextView(context: Context?, attrs: AttributeSet?) : androidx.appcompat.widget.AppCompatTextView(context, attrs) {
    private val COLORS = intArrayOf(
        Color.parseColor("#E91E63"),
        Color.parseColor("#673AB7"),
        Color.parseColor("#3F51B5"),
        Color.parseColor("#2196F3"),
        Color.parseColor("#009688"),
        Color.parseColor("#FF9800"),
        Color.parseColor("#FF5722"),
        Color.parseColor("#795548")
    )
    private val TEXT_SIZES = intArrayOf(16, 22, 28)
    private val random = Random()
    private val CORNER_RADIUS = Utils.dpToPixel(4F).toInt()
    private val X_PADDING = Utils.dpToPixel(16F).toInt()
    private val Y_PADDING = Utils.dpToPixel(8F).toInt()

    private var paint = Paint(Paint.ANTI_ALIAS_FLAG)

    init {
        setTextColor(Color.WHITE)
        textSize = TEXT_SIZES[random.nextInt(3)].toFloat()
        paint.color = COLORS[random.nextInt(COLORS.size)]
        setPadding(X_PADDING, Y_PADDING, X_PADDING, Y_PADDING)
    }


    override fun onDraw(canvas: Canvas?) {
        canvas?.drawRoundRect(
            0F,
            0F,
            width.toFloat(),
            height.toFloat(),
            CORNER_RADIUS.toFloat(),
            CORNER_RADIUS.toFloat(),
            paint
        )
        super.onDraw(canvas)
    }
}