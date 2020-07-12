package io.github.halochn.algorithm.common

abstract class AbstractList<E>  :
    List<E> {

    protected var size: Int = 0

    override fun isEmpty(): Boolean {
        return size == 0
    }

    override fun add(element: E) :Boolean{
       return add(size, element)
    }

    protected fun checkPositionIndex(index: Int) {
        if (!isPositionIndex(index)) throw IndexOutOfBoundsException(outOfBoundsMsg(index))
    }

    /**
     * Tells if the argument is the index of a valid position for an
     * iterator or an add operation.
     */
    private  fun isPositionIndex(index: Int): Boolean {
        return index in 0..size
    }

    /**
     * Constructs an IndexOutOfBoundsException detail message.
     * Of the many possible refactorings of the error handling code,
     * this "outlining" performs best with both server and client VMs.
     */
    private fun outOfBoundsMsg(index: Int): String? {
        return "Index: $index, Size: $size"
    }
}