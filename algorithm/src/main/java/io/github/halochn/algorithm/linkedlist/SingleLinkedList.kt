package io.github.halochn.algorithm.linkedlist

import io.github.halochn.algorithm.common.AbstractList
import java.lang.StringBuilder

class SingleLinkedList<E> : AbstractList<E>() {

    var first: Node<E>? = null

    override fun add(index: Int, element: E): Boolean {
        checkPositionIndex(index)
        if (index == 0) {
            first = Node(element, first)
        } else {
            node(index - 1)?.let { prev ->
                prev.next = Node(element, prev.next)
            }
        }
        size++
        return true
    }

    override fun removeAt(index: Int): E {
        TODO("Not yet implemented")
    }

    override fun get(index: Int): E? {
        return node(index)?.item
    }

    override fun set(index: Int, element: E) {
        TODO("Not yet implemented")
    }

    override fun lastIndexOf(element: E?): Int {
        TODO("Not yet implemented")
    }

    private fun node(index: Int): Node<E>? {
        checkPositionIndex(index)
        var x = first
        for (i in 0 until index) {
            x = x?.next
        }
        return x
    }

    class Node<E> constructor(var item: E?, var next: Node<E>?) {
        constructor(item: E?) : this(item, null)
    }

    override fun toString(): String {
        val stringBuilder = StringBuilder()
        var x = first
        for (i in 0 until size) {
            if (i != 0) {
                stringBuilder.append(" -> ")
            }
            stringBuilder.append(x?.item.toString())
            x = x?.next
        }
        return stringBuilder.toString()
    }
}