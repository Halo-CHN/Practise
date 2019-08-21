package com.halochn.kaixue_kt.lesson_2

class ArrayAndList {

    companion object {
        const val START_NUM = 1
        const val END_NUM = 100000
        const val SIZE = END_NUM - START_NUM + 1
    }

    fun test() {
        var total = 0L

        println("array start " + System.currentTimeMillis())
        val array = Array(END_NUM) { i -> i + START_NUM }
        for (element in array) {
            total += element
        }
        println("array evg " + total / SIZE)
        println("array end " + System.currentTimeMillis())

        total = 0L

        println("intArray start " + System.currentTimeMillis())
        val intArray = IntArray(END_NUM) { i -> i + START_NUM }
        for (element in intArray) {
            total += element
        }
        println("intArray evg " + total / SIZE)
        println("intArray end " + System.currentTimeMillis())

        total = 0L

        println("list start " + System.currentTimeMillis())
        val list = List(END_NUM) { i -> i + START_NUM }
        for (element in list) {
            total += element
        }
        println("list evg " + total / SIZE)
        println("list end " + System.currentTimeMillis())
    }

    private var array2 = Array(END_NUM) { START_NUM }
    private var intArray2 = IntArray(END_NUM - START_NUM + 1)
    private var list2 = mutableListOf<Int>()


    fun test2() {

        var total = 0L

        println("array start " + System.currentTimeMillis())
        for (i in START_NUM..END_NUM) {
            array2[i - 1] = i
        }
        for (element in array2) {
            total += element
        }
        println("array evg " + total / SIZE)
        println("array end " + System.currentTimeMillis())

        total = 0L

        println("intArray start " + System.currentTimeMillis())
        for (i in START_NUM..END_NUM) {
            intArray2[i - 1] = i
        }
        for (element in intArray2) {
            total += element
        }
        println("intArray evg " + total / SIZE)
        println("intArray end " + System.currentTimeMillis())

        total = 0L

        println("list start " + System.currentTimeMillis())
        for (i in START_NUM..END_NUM) {
            list2.add(i - 1, i)
        }
        for (element in list2) {
            total += element
        }
        println("list evg " + total / SIZE)
        println("list end " + System.currentTimeMillis())

    }
}