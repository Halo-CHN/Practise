package io.github.halochn.algorithm

import io.github.halochn.algorithm.array.HaloMutableList
import io.github.halochn.algorithm.linkedlist.SingleLinkedList

fun main(args: Array<String>) {
    // testList()
    testLinkedList()
}

fun testLinkedList() {
    val linkedList = SingleLinkedList<Int>().apply {
        add(1)
        add(2)
        add(3)
        println(this)
    }
}


private fun testList() {
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

    intMutableList.clear()
    println(intMutableList)
}

class ListNode(var `val`: Int) {
    var next: ListNode? = null
}

class Solution {
    fun isPalindrome(head: ListNode?): Boolean {
        var size = 0
        var cur = head
        while (cur != null) {
            size++
            cur = cur?.next;
        }
        var palindromeIndex = size / 2
        var palindromeResidue = size % 2
        palindromeIndex += palindromeResidue
        cur = head;
        var prev: ListNode? = null
        var counter = 0
        while (cur != null) {
            if (counter < palindromeIndex) {
                var next = cur.next
                cur.next = prev
                prev = cur
                cur = next
            } else if (counter == palindromeIndex) {
                cur = if (palindromeResidue == 0) cur else cur.next
                if (cur?.`val` != prev?.`val`) {
                    return false
                }
                cur = cur?.next
                prev = prev?.next
            } else {
                if (cur?.`val` != prev?.`val`) {
                    return false
                }
                cur = cur?.next
                prev = prev?.next
            }
            counter++
        }
        return true
    }
}