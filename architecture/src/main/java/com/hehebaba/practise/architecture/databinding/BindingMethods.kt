package com.hehebaba.practise.architecture.databinding

import android.widget.ImageView
import androidx.databinding.BindingMethod
import androidx.databinding.BindingMethods

@BindingMethods(
    BindingMethod(
        type = ImageView::class,
        attribute = "android:tint",
        method = "setImageTintList"
    )
)
class BindingMethods {
}