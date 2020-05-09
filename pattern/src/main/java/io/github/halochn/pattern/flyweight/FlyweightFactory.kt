package io.github.halochn.pattern.flyweight

class FlyweightFactory private constructor() {

    private var flyweights: HashMap<String, Flyweight?> = HashMap()

    companion object {
        fun getInstance(): FlyweightFactory {
            return Singleton.instance
        }
    }

    private object Singleton {
        val instance: FlyweightFactory = FlyweightFactory()
    }

    public fun getFlyweight(fKey: String): Flyweight? {
        if (flyweights.containsKey(fKey)) {
            return flyweights[fKey]
        } else {
            val flyweight = when (fKey) {
                "1" -> Sub1Class(fKey)
                "2" -> Sub2Class(fKey)
                else -> null
            }
            flyweights[fKey] = flyweight
            return flyweight
        }
    }
}