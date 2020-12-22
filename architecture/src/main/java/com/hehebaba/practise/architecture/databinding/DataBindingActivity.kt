package com.hehebaba.practise.architecture.databinding

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.hehebaba.practise.architecture.R

class DataBindingActivity : AppCompatActivity() {

    private lateinit var dataBindingBinding: DataBindingClass

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBindingBinding = DataBindingUtil.setContentView(this, R.layout.activity_data_binding)
        //setContentView(R.layout.activity_data_binding)
        dataBindingBinding.handlers = MyHandlers()
    }
}