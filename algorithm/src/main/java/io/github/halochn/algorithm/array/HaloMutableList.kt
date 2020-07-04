package io.github.halochn.algorithm.array

class HaloMutableList<E> : MutableList<E?> {

    companion object {
        private val DEFAULT_CAPACITY = 16
    }

    private var capacity = 0

    private var _size = 0

    private var elements: Array<E?>

    override val size: Int
        get() = _size

    constructor() : this(DEFAULT_CAPACITY)

    @Suppress("UNCHECKED_CAST")
    constructor(capacity: Int) {
        this.capacity = capacity
        elements = arrayOfNulls<Any>(capacity) as Array<E?>
    }

    constructor(elements: Collection<E?>) : this(elements.size) {
        elements.forEach {
            add(it)
        }
    }

    override fun contains(element: E?): Boolean {
        return indexOf(element) >= 0
    }

    override fun containsAll(elements: Collection<E?>): Boolean {
        TODO("Not yet implemented")
    }

    override fun get(index: Int): E? {
        checkIndexRange(index)
        return elements[index]
    }

    override fun indexOf(element: E?): Int {
        for (i in elements.indices) {
            if (elements[i] == element) return i
        }
        return -1
    }

    override fun isEmpty(): Boolean {
        return size == 0
    }

    override fun iterator(): MutableIterator<E?> {
        TODO("Not yet implemented")
    }

    override fun lastIndexOf(element: E?): Int {
        if (isEmpty()) {
            return -1
        }
        for (i in size - 1 downTo 0) {
            if (elements[i] == element) {
                return i
            }
        }
        return -1
    }

    override fun add(element: E?): Boolean {
        add(size, element)
        return true
    }

    override fun add(index: Int, element: E?) {
        checkIndexRangeForAdd(index)
        ensureCapacity(size + 1)
        for (i in size downTo index) {
            if (i > index) {
                elements[i] = elements[i - 1]
            }
        }
        elements[index] = element
        _size++;
    }

    override fun addAll(index: Int, elements: Collection<E?>): Boolean {
        TODO("Not yet implemented")
    }

    override fun addAll(elements: Collection<E?>): Boolean {
        TODO("Not yet implemented")
    }

    override fun clear() {
        for (i in 0 until size) {
            if (elements[i] != null) elements[i] = null else break
        }
        _size = 0
    }

    override fun listIterator(): MutableListIterator<E?> {
        TODO("Not yet implemented")
    }

    override fun listIterator(index: Int): MutableListIterator<E?> {
        TODO("Not yet implemented")
    }

    override fun remove(element: E?): Boolean {
        TODO("Not yet implemented")
    }

    override fun removeAll(elements: Collection<E?>): Boolean {
        TODO("Not yet implemented")
    }

    override fun removeAt(index: Int): E? {
        checkIndexRange(index)
        val old = elements[index]
        for (i in index until size) {
            if (i < size - 1) {
                elements[i] = elements[i + 1]
            }
        }
        elements[--_size] = null
        return old
    }

    override fun retainAll(elements: Collection<E?>): Boolean {
        TODO("Not yet implemented")
    }

    override fun set(index: Int, element: E?): E? {
        checkIndexRange(index)
        val result = elements[index]
        elements[index] = element
        return result
    }

    override fun subList(fromIndex: Int, toIndex: Int): MutableList<E?> {
        TODO("Not yet implemented")
    }

    private fun checkIndexRange(index: Int) {
        if (index < 0 || index >= size) {
            throw IndexOutOfBoundsException("index:$index,size:$size")
        }
    }

    private fun checkIndexRangeForAdd(index: Int) {
        if (index < 0 || index > size) {
            throw IndexOutOfBoundsException("index:$index,size:$size")
        }
    }

    private fun ensureCapacity(capacity: Int) {
        if (capacity > elements.size) {
            val newCapacity = capacity + capacity.shr(1)
            elements = (arrayOfNulls<Any>(newCapacity) as Array<E?>).apply {
                System.arraycopy(elements, 0, this, 0, elements.size)
            }
        }
    }


    override fun toString(): String {
        val stringBuilder = StringBuilder()

        for (i in 0 until size) {
            stringBuilder.append(" ${elements[i]}")
        }

        return stringBuilder.toString()
    }
}