package com.halochn.kaixue_kt.lesson_2

class ArrayAndList {

    companion object {
        const val START_NUM = 1
        const val END_NUM = 100000
        const val SIZE = END_NUM - START_NUM + 1
    }

    private var array = Array(END_NUM) { START_NUM }
    private var intArray = IntArray(END_NUM - START_NUM + 1)
    private var list = mutableListOf<Int>()

    fun test() {
        var total = 0L

        println("array start " + System.currentTimeMillis())
        for (i in START_NUM..END_NUM) {
            array[i - 1] = i
        }
        for (element in array) {
            total += element
        }
        println("array evg " + total / SIZE)
        println("array end " + System.currentTimeMillis())

        total = 0L

        println("intArray start " + System.currentTimeMillis())
        for (i in START_NUM..END_NUM) {
            intArray[i - 1] = i
        }
        for (element in intArray) {
            total += element
        }
        println("intArray evg " + total / SIZE)
        println("intArray end " + System.currentTimeMillis())

        total = 0L

        println("list start " + System.currentTimeMillis())
        for (i in START_NUM..END_NUM) {
            list.add(i - 1, i)
        }
        for (element in list) {
            total += element
        }
        println("list evg " + total / SIZE)
        println("list end " + System.currentTimeMillis())
    }
}