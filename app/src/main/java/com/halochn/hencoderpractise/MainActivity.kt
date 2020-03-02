package com.halochn.hencoderpractise

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.get
import androidx.core.view.setMargins
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(mToolbar)
        mToolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp)
        mToolbar.setNavigationOnClickListener {
            onBackPressed()
        }
         (mToolbar.layoutParams as LinearLayout.LayoutParams).let {
            it.setMargins(0, StatusBarUtil.getStatusBarHeight(this@MainActivity), 0, 0)
//            it
        }

        with(window) {
            when {
                Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP -> {
                    clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS or WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION)
                    addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
                    statusBarColor = Color.TRANSPARENT
                    decorView.systemUiVisibility =
                        decorView.systemUiVisibility or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                }
                else -> {
                    addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
                }
            }
            (decorView as ViewGroup).apply {
                val translucentView = StatusTranslucentView(applicationContext, null)
                val layoutParams: LinearLayout.LayoutParams =
                    LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        StatusBarUtil.getStatusBarHeight(this@MainActivity)
                    )
                translucentView.layoutParams = layoutParams
                /*for (index in 0 until childCount) {
                    if (this[index] is LinearLayout) {
                        (this[index] as LinearLayout)?.apply {
                            addView(translucentView, 0)
                        }
                        break
                    }
                }*/
                addView(translucentView)
            }
        }
    }
}
