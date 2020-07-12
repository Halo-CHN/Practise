package io.github.halochn.pattern.flyweight

class Sub1Class(var name: String) : Flyweight(name) {
    override fun getExtrinsicState(): String {
        return "Sub1Class"
    }
}