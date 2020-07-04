package io.github.halochn.layout

import android.content.Context
import android.graphics.Rect
import android.util.AttributeSet
import android.view.ViewGroup
import kotlin.math.max

class TagLayout(context: Context?, attrs: AttributeSet?) : ViewGroup(context, attrs) {

    private var mChildRectList: MutableList<Rect> = ArrayList()

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        var heightUsed = 0
        var widthUsed = 0
        var lineHeight = 0
        var lineWidthUsed = 0
        var measureSpecWidth = MeasureSpec.getSize(widthMeasureSpec)
        val measureSpecMode = MeasureSpec.getMode(widthMeasureSpec)
        for (i in 0 until childCount) {
            val child = getChildAt(i)
            measureChildWithMargins(
                child,
                widthMeasureSpec,
                0,
                heightMeasureSpec,
                heightUsed
            )

            if (measureSpecMode != MeasureSpec.UNSPECIFIED && lineWidthUsed + child.measuredWidth > measureSpecWidth) {
                heightUsed += lineHeight
                lineWidthUsed = 0
                measureChildWithMargins(
                    child,
                    widthMeasureSpec,
                    0,
                    heightMeasureSpec,
                    heightUsed
                )
            }


            var childBounds: Rect?
            if (mChildRectList.size > i) {
                childBounds = mChildRectList[i]
            } else {
                childBounds = Rect()
                mChildRectList.add(childBounds)
            }
            childBounds.set(
                lineWidthUsed,
                heightUsed,
                lineWidthUsed + child.measuredWidth,
                heightUsed + child.measuredHeight
            )

            lineWidthUsed += child.measuredWidth
            widthUsed = max(widthUsed, lineWidthUsed)
            lineHeight = max(child.measuredHeight, lineHeight)

        }
        val measuredWidth = widthUsed
        heightUsed += lineHeight
        val measuredHeight = heightUsed
        setMeasuredDimension(measuredWidth, measuredHeight)
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        for (i in 0 until childCount) {
            getChildAt(i).layout(
                mChildRectList[i].left,
                mChildRectList[i].top,
                mChildRectList[i].right,
                mChildRectList[i].bottom
            )
        }
    }

    override fun generateLayoutParams(attrs: AttributeSet?): LayoutParams {
        return MarginLayoutParams(context, attrs)
    }
}