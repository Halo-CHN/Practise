package com.halochn.text_measure

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.collection.ArrayMap
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private val text = "微型车中拥有更高的安全性,设计风格艺术艺术 微型车中拥有更高的安全性,设计风格艺术艺术"
    private val text1 = "驾驶灵活，泊车十分方便，搭载1.8升发动机"
    private val text2 = "对于国内来说价格昂贵，维修保养价格相对较高"
    private val text3 = "驾驶灵活，泊车十分方便，搭载1.8升发动机"

    private val textMap = linkedMapOf<String, String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun change(view: View) {
        textMap.clear()
        textMap["外观"] = text
        textMap["内饰"] = text1
        textMap["动力"] = text2
        textMap["其它"] = text3
        when (Random.nextInt(1, 4)) {
            1 -> textMap.remove("外观")
            2 -> textMap.remove("内饰")
            3 -> textMap.remove("动力")
            4 -> textMap.remove("其它")
        }
        majorPeriodDescriptionView.setTitleTextMap(textMap)
        multiLineTextView.setTitleTextMap(textMap)
    }
}
