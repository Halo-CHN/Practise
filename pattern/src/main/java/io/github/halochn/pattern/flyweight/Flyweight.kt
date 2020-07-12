package io.github.halochn.pattern.flyweight

open abstract class Flyweight constructor(private var name: String) {
    var intrinsicState: String? = null

    abstract fun getExtrinsicState(): String
}