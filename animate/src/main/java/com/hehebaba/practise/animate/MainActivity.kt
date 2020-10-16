package com.hehebaba.practise.animate

import android.animation.ValueAnimator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.hehebaba.practise.animate.widget.RoundDrawable
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val animator = ValueAnimator.ofFloat(0F, 100F).apply {
        startDelay = 500
        duration = 1000
        addUpdateListener {
            imageView.translationX =  it.animatedValue as Float
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        imageView.setImageDrawable(RoundDrawable())
        imageView.setOnClickListener {
            animator.start()
        }
    }
}
