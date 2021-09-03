package com.hehebaba.practise.animate.widget

import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.graphics.drawable.ColorDrawable
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.util.AttributeSet
import com.hehebaba.practise.animate.R
import com.hehebaba.practise.animate.Utils

class MaterialEditText(context: Context, attrs: AttributeSet?) :
    androidx.appcompat.widget.AppCompatEditText(context, attrs) {

    private val TEXT_SIZE = Utils.dp2px(12)
    private val TEXT_MARGIN = Utils.dp2px(8)
    private val VERTICAL_OFFSET = Utils.dp2px(38)
    private val HORIZONTAL_OFFSET = Utils.dp2px(5)
    private val EXTRA_OFFSET = Utils.dp2px(16)
    private var floatingLabelShown = false
    private val topDrawable = ColorDrawable(Color.TRANSPARENT).apply {
        setBounds(0, 0, 1, (TEXT_SIZE + TEXT_MARGIN).toInt())
    }

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        textSize = TEXT_SIZE
    }
    private val backgroundPadding = Rect()

    var useFloatingLabel: Boolean = false
        set(value) {
            field = value
            refreshPadding()
        }

    var floatingLabelFraction = 0f
        set(value) {
            field = value
            invalidate()
        }

    private val animator by lazy {
        ObjectAnimator.ofFloat(this@MaterialEditText, "floatingLabelFraction", 1f)
    }

    init {
        context.obtainStyledAttributes(attrs, R.styleable.MaterialEditText).run {
            useFloatingLabel = getBoolean(R.styleable.MaterialEditText_userFloatingLabel, false)
            recycle()
        }
        refreshPadding()
        addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (useFloatingLabel) {
                    if (!floatingLabelShown && !TextUtils.isEmpty(s)) {
                        floatingLabelShown = true
                        animator.start()
                    } else if (floatingLabelShown && TextUtils.isEmpty(s)) {
                        floatingLabelShown = false
                        animator.reverse()
                    }
                }
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })
    }

    private fun refreshPadding() {
        background.getPadding(backgroundPadding)
        setPadding(
            backgroundPadding.left,
            when (useFloatingLabel) {
                true -> (backgroundPadding.top + TEXT_SIZE + TEXT_MARGIN).toInt()
                false -> backgroundPadding.top
            },
            backgroundPadding.right,
            backgroundPadding.bottom
        )
//        setCompoundDrawablesRelative(
//            null,
//            when (useFloatingLabel) {
//                true -> topDrawable
//                false -> null
//            },
//            null,
//            null
//        )
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.run {
            if (useFloatingLabel) {
                save()
                paint.alpha = (0xff * floatingLabelFraction).toInt()
                val extraOffset = -EXTRA_OFFSET * floatingLabelFraction
                drawText(hint.toString(), HORIZONTAL_OFFSET, VERTICAL_OFFSET + extraOffset, paint)
                restore()
            }
        }
    }
}