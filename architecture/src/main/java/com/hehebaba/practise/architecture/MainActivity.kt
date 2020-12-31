package com.hehebaba.practise.architecture

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.Observer
import com.hehebaba.practise.architecture.hilt.ExampleApp
import com.hehebaba.practise.architecture.viewmodel.MyViewModel

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val model: MyViewModel by viewModels()
        model.getUsers().observe(this, Observer { users ->

        })

        val applicationModel: AndroidViewModel by viewModels()

        println("AndroidViewModel ${applicationModel.getApplication<ExampleApp>()}")
    }
}
