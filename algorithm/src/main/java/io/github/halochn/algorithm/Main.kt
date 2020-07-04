package io.github.halochn.algorithm

import io.github.halochn.algorithm.array.HaloMutableList

fun main(args: Array<String>) {
    val intMutableList = HaloMutableList<Int>()
    intMutableList.add(1)
    intMutableList.add(2)
    intMutableList.add(3)
    intMutableList.add(4)
    intMutableList.add(3)
    intMutableList.add(2)
    println("index of 3 :${intMutableList.indexOf(3)}")
    println("lastIndexOf 3:${intMutableList.lastIndexOf(3)}")
    println(intMutableList)

    for (i in 0..20) {
        intMutableList.add(i)
    }
    println(intMutableList)

    intMutableList.add(3, 30)
    intMutableList.add(10, 100)
    intMutableList.add(20, 200)

    println(intMutableList)

    intMutableList.removeAt(20)
    println(intMutableList)
    intMutableList.removeAt(10)
    println(intMutableList)
    intMutableList.removeAt(3)
    println(intMutableList)
}