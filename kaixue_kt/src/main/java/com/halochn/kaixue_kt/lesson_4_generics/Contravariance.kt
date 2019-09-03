package com.halochn.kaixue_kt.lesson_4_generics

fun main() {
    val fruits = arrayOfNulls<Fruit>(1)

    Generics.fill(fruits, Banana())

    fruits[0]?.intro()

    println("======================")

    val fruitsOut = arrayOfNulls<Fruit>(5)
    fruitsOut[0] = Apple()
    fruitsOut[1] = Banana()
    fruitsOut[2] = Apple()
    fruitsOut[3] = Banana()
    fruitsOut[4] = Apple()

    val fruitsIn = arrayOfNulls<Fruit>(5)
    Generics.copy(fruitsOut, fruitsIn)

    for (item in fruitsIn.indices) {
        fruitsIn[item]?.intro()
    }
}


class Generics {
    companion object {
        fun <T> fill(fruits: Array<in T>, t: T) {
            fruits[0] = t
        }

        fun <T> copy(tOut: Array<out T>, tIn: Array<in T>) {
            for (i in tOut.indices) {
                tIn[i] = tOut[i]
            }
        }
    }
}

open class Fruit(private var nameCN: String) {
    fun intro() {
        println(nameCN)
    }

    init {
        nameCN = if (nameCN.isEmpty()) "水果" else nameCN
    }
}

class Banana : Fruit("香蕉")

class Apple : Fruit("苹果")