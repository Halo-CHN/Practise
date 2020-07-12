package io.github.halochn.pattern

import io.github.halochn.pattern.flyweight.FlyweightFactory

class Main {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val f1 = FlyweightFactory.getInstance().getFlyweight("1")
            f1?.intrinsicState = "11"
            val f2 = FlyweightFactory.getInstance().getFlyweight("1")
            f2?.intrinsicState = "22"
            println(f1 === f2)
            println("f1 intrinsicState = ${f1?.intrinsicState} extrinsicState = ${f1?.getExtrinsicState()}")
            println("f2 intrinsicState = ${f2?.intrinsicState} extrinsicState = ${f2?.getExtrinsicState()}")
            println(f2?.intrinsicState)
            val f3 = FlyweightFactory.getInstance().getFlyweight("2")
            val f4 = FlyweightFactory.getInstance().getFlyweight("2")
            val f5 = FlyweightFactory.getInstance().getFlyweight("2")
        }
    }
}