package com.halochn.kaixue_kt

import kotlin.math.pow

class MutableStack<E>(vararg items: E) {              // 1

    private val elements = items.toMutableList()

    fun push(element: E) = elements.add(element)        // 2

    fun peek(): E = elements.last()                     // 3

    fun pop(): E = elements.removeAt(elements.size - 1)

    fun isEmpty() = elements.isEmpty()

    fun size() = elements.size

    override fun toString() = "MutableStack(${elements.joinToString()})"
}

fun <E> mutableStackOf(vararg element: E) = MutableStack(*element)


enum class Color(val rgb: Int) {                      // 1
    RED(0xFF0000),                                    // 2
    GREEN(0x00FF00),
    BLUE(0x0000FF),
    YELLOW(0xFFFF00);

    fun containsRed() = (this.rgb and 0xFF0000 != 0)  // 3
}

// 1
sealed class Mammal(val name: String)

class Cat(val catName: String) : Mammal(catName)                                        // 2
class Human(val humanName: String, val job: String) : Mammal(humanName)

fun greetMammal(mammal: Mammal): String {
    when (mammal) {                                                                     // 3
        is Human -> return "Hello ${mammal.name}; You're working as a ${mammal.job}"    // 4
        is Cat -> return "Hello ${mammal.name}"                                         // 5
        else -> return ""
    }                                                                                   // 6
}

fun calculate(x: Int, y: Int, operation: (Int, Int) -> Int): Int {
    return operation(x, y)
}

fun operation(): (Int) -> Int = ::square


fun square(x: Int): Int {
    return x.toDouble().pow(x.toDouble()).toInt()
}

fun main1() {

    val upperCase1: (String) -> String = { str: String -> str.toUpperCase() }
    val upperCase2: (String) -> String = { str -> str.toUpperCase() }
    val upperCase3 = { str: String -> str.toUpperCase() }
    val upperCase4: (String) -> String = { it.toUpperCase() }
    val upperCase5: (String) -> String = String::toUpperCase

    val list = MutableList(10, operation())

    list.add(0)
    list.add(2)
    list.add(1)


    println(list.joinToString())

    square(3)
    println(operation().run { this(3) })
}

val systemUsers: MutableList<Int> = mutableListOf(1, 2, 3)        // 1
val sudoers: ArrayList<Int> = systemUsers as ArrayList<Int>                              // 2

fun addSudoer(newUser: Int) {                                     // 3
    systemUsers.add(newUser)
}

fun getSysSudoers(): ArrayList<Int> {                                  // 4
    return sudoers
}

interface SoundBehavior {                                                          // 1
    fun makeSound()
}

class ScreamBehavior(val n:String): SoundBehavior {                                // 2
    override fun makeSound() = println("${n.toUpperCase()} !!!")
}

class RockAndRollBehavior(val n:String): SoundBehavior {                           // 2
    override fun makeSound() = println("I'm The King of Rock 'N' Roll: $n")
}

// Tom Araya is the "singer" of Slayer
class TomAraya(n:String): SoundBehavior by ScreamBehavior(n)                       // 3

// You should know ;)
class ElvisPresley(n:String): SoundBehavior by RockAndRollBehavior(n)              // 3

fun findMinMax(list: List<Int>): Pair<Int, Int> {
    // do the math
    return Pair(50, 100)
}
data class User(val username: String, val email: String)    // 1

fun getUser() = User("Mary", "mary@somewhere.com")

fun main() {
    val user = getUser()
    val (username, email) = user                            // 2
    println(username == user.component1())                  // 3

    val (_, emailAddress) = getUser()                       // 4
    println(emailAddress)
}