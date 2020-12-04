package com.hehebaba.practise.architecture.databinding

import androidx.databinding.Observable
import androidx.databinding.ObservableField
import androidx.databinding.ObservableInt

class User {
    val name = ObservableField<String>()
    val age = ObservableInt()
}

fun main() {
    var user = User()

    user.name.addOnPropertyChangedCallback(object : Observable.OnPropertyChangedCallback() {
        override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
            println(user.name.get())
        }
    })

    user.name.set("修改了")

    user.age.addOnPropertyChangedCallback(object :Observable.OnPropertyChangedCallback(){
        override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
            println(user.age.get())
        }
    })

    user.age.set(30)
}