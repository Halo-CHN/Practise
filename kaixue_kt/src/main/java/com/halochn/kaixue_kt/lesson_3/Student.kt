package com.halochn.kaixue_kt.lesson_3

class Student constructor(var name: String, var age: Int) {

    constructor(name: String = "nobody") : this(name, 0)
    constructor(age: Int) : this("")
    constructor() : this("")

    fun show() = println("Hi $name, are u ${this.age} years old ？")
}

fun main() {
    /*val student = Student("Android Rookie")
    student.show()*/
    find3()
}

fun find3() {
    val params = intArrayOf(21, 40, 11, 33, 78)
    val result: List<Int> = params.filter { i ->
        i % 3 == 0
    }
    for (item in result) {
        println("Fount num : $item")
    }
}