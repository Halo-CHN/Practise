package com.hehebaba.practise.architecture.databinding

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.BindingAdapter
import androidx.databinding.BindingMethod
import androidx.databinding.BindingMethods
import androidx.databinding.DataBindingUtil
import com.hehebaba.practise.architecture.R
import com.squareup.picasso.Picasso


class DataBindingAdapterActivity : AppCompatActivity() {

    private lateinit var bindingAdapterBinding: ActivityDataBindingAdapterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingAdapterBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_data_binding_adapter)
        bindingAdapterBinding.handler = MyHandlers()
    }
}