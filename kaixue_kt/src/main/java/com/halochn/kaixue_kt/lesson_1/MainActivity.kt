package com.halochn.kaixue_kt.lesson_1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.core.widget.TextViewCompat
import com.halochn.kaixue_kt.R

class MainActivity : AppCompatActivity() {

    private lateinit var view: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        view = findViewById(R.id.tv_HelloWorld)

        if (view is TextView) {
            (view as?TextView)?.text = "view ok !!"
        }

        printId(view)
    }

    private fun printId(view: View?) {
        println("view id : " + view?.id)
    }
}