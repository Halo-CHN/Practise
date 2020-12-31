package com.hehebaba.practise.architecture.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedViewModel<T> : ViewModel() {
    val selected = MutableLiveData<T>()

    fun select(item: T) {
        selected.value = item
    }
}