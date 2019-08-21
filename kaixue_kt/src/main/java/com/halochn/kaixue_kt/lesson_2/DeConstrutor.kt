package com.halochn.kaixue_kt.lesson_2

class DeConstrutor private constructor() {

    fun cons() {
        println("DeConstrutor Successed @@")
    }

    companion object {
        fun newInstance(): DeConstrutor {
            return DeConstrutor()
        }
    }
}

object  DeConstrutor2{
    fun cons() {
        println("DeConstrutor2 Successed @@")
    }
}