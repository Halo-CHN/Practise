package com.hehebaba.practise.architecture.viewbinding

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.hehebaba.practise.architecture.databinding.ActivityViewBindingBinding

//import kotlinx.android.synthetic.main.activity_view_binding.*

class ViewBindingActivity : AppCompatActivity() {

    lateinit var binding: ActivityViewBindingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityViewBindingBinding.inflate(layoutInflater)
        val root = binding.root
        setContentView(root)
        initView()
    }

    private fun initView() {
        binding.tvBindingTest.text = "${this@ViewBindingActivity::class.simpleName}"
    }
}