package io.github.halochn.pattern.flyweight

class Sub2Class(name: String) : Flyweight(name) {
    override fun getExtrinsicState(): String {
        return "Sub2Class"
    }
}