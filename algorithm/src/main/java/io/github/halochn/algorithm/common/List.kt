package io.github.halochn.algorithm.common

interface List<E> {

    fun add(element: E): Boolean

    fun add(index: Int, element: E):Boolean

    fun removeAt(index: Int): E

    fun get(index: Int): E?

    fun set(index: Int, element: E)

    fun isEmpty(): Boolean

    fun lastIndexOf(element: E?): Int
}