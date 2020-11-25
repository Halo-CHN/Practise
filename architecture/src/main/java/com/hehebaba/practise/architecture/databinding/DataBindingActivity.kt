package com.hehebaba.practise.architecture.databinding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import androidx.databinding.DataBindingUtil
import com.hehebaba.practise.architecture.R
import kotlinx.android.synthetic.main.activity_data_binding.*

class DataBindingActivity : AppCompatActivity() {

    private lateinit var dataBindingBinding: ActivityDataBindingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBindingBinding = DataBindingUtil.setContentView(this, R.layout.activity_data_binding)
        //setContentView(R.layout.activity_data_binding)
        dataBindingBinding.handlers = MyHandlers()
    }
}