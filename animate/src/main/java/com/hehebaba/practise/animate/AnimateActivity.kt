package com.hehebaba.practise.animate

import android.animation.AnimatorSet
import android.animation.Keyframe
import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.os.Bundle
import android.util.Log
import android.view.animation.OvershootInterpolator
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_animate.*

class AnimateActivity : AppCompatActivity() {

    private lateinit var animatorSet: AnimatorSet
    private var viewWidth: Int = 0
    private var viewHeight: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_animate)

        // 圆形放大动画
        /*
        val radiosAnimator = ObjectAnimator.ofFloat(view, "radius", Utils.dp2px(150)).run {
            duration = 2000
            startDelay = 2000
            start()
        }
        */

        window.decorView.viewTreeObserver.addOnGlobalLayoutListener {
            if (viewWidth == 0 || viewHeight == 0) {
                viewWidth = view.width
                viewHeight = view.height

                playWithAnimatorSet()
                //playWithPropertyValuesHolder()
                playWithKeyFrame()
            }
        }
    }

    private fun playWithKeyFrame() {
        val together = true
        val keyframe0 = Keyframe.ofFloat(0f, viewWidth / 2f)
        val keyframe1 = Keyframe.ofFloat(0.8f, viewWidth / 1.5f)
        val keyframe2 = Keyframe.ofFloat(1f, viewWidth.toFloat()).apply {
            interpolator = OvershootInterpolator()
        }
        val offsetXPVHolder =
            if (!together)
                PropertyValuesHolder.ofFloat(
                    "offsetX", viewWidth.toFloat() / 2f, viewWidth.toFloat()
                ).apply {
                    setKeyframes(keyframe0, keyframe1, keyframe2)
                }
            else PropertyValuesHolder.ofKeyframe("offsetX", keyframe0, keyframe1, keyframe2).apply {
            }

        ObjectAnimator.ofPropertyValuesHolder(view, offsetXPVHolder).run {
            duration = 2000
            startDelay = 5000
            start()
        }
    }

    /**
     *
     */
    private fun playWithPropertyValuesHolder() {
        val offsetXPVHolder = PropertyValuesHolder.ofFloat("offsetX", viewWidth.toFloat())
        val offsetYPVHolder = PropertyValuesHolder.ofFloat("offsetY", 0f)
        ObjectAnimator.ofPropertyValuesHolder(view, offsetXPVHolder, offsetYPVHolder).run {
            duration = 2000
            startDelay = 5000
            start()
        }
    }

    /**
     * 使用 animator set
     */
    private fun playWithAnimatorSet() {
        Log.d("animate", "view.width: $viewWidth")
        Log.d("animate", "view.height: $viewHeight")
        // 位移动画
        val translationXAnimator =
            ObjectAnimator.ofFloat(view, "offsetX", 0f, viewWidth / 2f).apply {
                duration = 2000
                startDelay = 2000
            }
        val translationYAnimator =
            ObjectAnimator.ofFloat(view, "offsetY", 0f, viewHeight / 2f).apply {
                duration = 2000
                startDelay = 2000
            }
        animatorSet = AnimatorSet().apply {
            playTogether(translationXAnimator, translationYAnimator)
            startDelay = 1000
        }
        animatorSet.start()
    }
}