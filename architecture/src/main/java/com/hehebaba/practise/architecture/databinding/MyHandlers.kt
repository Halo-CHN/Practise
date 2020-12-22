package com.hehebaba.practise.architecture.databinding

import android.view.View
import android.widget.TextView
import android.widget.Toast

class MyHandlers {
    fun onClickFriend(view: View) {
        view.takeIf { it is TextView }?.apply {
            Toast.makeText(view.context, (this as TextView).text, Toast.LENGTH_SHORT).show()
        }
    }

    fun onCheckedChanged(view: View, isChecked: Boolean) {
        Toast.makeText(view.context, isChecked.toString(), Toast.LENGTH_SHORT).show()
    }

    fun layoutChanged(view: View) {

    }
}