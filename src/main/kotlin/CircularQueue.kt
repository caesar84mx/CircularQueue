package com.caesar84mx

class CircularQueue<T>(private val capacity: Int) : MutableCollection<T> {
    init {
        require(capacity > 0) { "Capacity must be greater than zero" }
    }

    private val list: MutableList<T?> = MutableList(capacity) { null }
    private var front = 0
    private var rear = -1
    private var currentSize = 0

    override val size: Int
        get() = currentSize

    override fun isEmpty(): Boolean = currentSize == 0

    override fun contains(element: T): Boolean {
        for (i in 0 until currentSize) {
            if (list[(front + i) % capacity] == element) {
                return true
            }
        }
        return false
    }

    override fun iterator(): MutableIterator<T> = object : MutableIterator<T> {
        private var currentIndex = front
        private var elementsIterated = 0

        override fun hasNext(): Boolean = elementsIterated < currentSize

        override fun next(): T {
            if (!hasNext()) throw NoSuchElementException()
            val element = list[currentIndex] ?: throw NoSuchElementException()
            currentIndex = (currentIndex + 1) % capacity
            elementsIterated++
            return element
        }

        override fun remove() {
            throw UnsupportedOperationException("Remove not supported in this iterator")
        }
    }

    override fun add(element: T): Boolean {
        if (currentSize == capacity) {
            // When the queue is full, shift elements to the left
            front = (front + 1) % capacity
            rear = (rear + 1) % capacity
            list[rear] = element
        } else {
            rear = (rear + 1) % capacity
            list[rear] = element
            currentSize++
        }
        return true
    }

    override fun remove(element: T): Boolean {
        for (i in 0 until currentSize) {
            val index = (front + i) % capacity
            if (list[index] == element) {
                for (j in i until currentSize - 1) {
                    list[(front + j) % capacity] = list[(front + j + 1) % capacity]
                }
                list[(front + currentSize - 1) % capacity] = null
                currentSize--
                if (currentSize == 0) {
                    front = 0
                    rear = -1
                } else {
                    rear = (front + currentSize - 1) % capacity
                }
                return true
            }
        }
        return false
    }

    override fun containsAll(elements: Collection<T>): Boolean {
        for (element in elements) {
            if (!contains(element)) {
                return false
            }
        }
        return true
    }

    override fun addAll(elements: Collection<T>): Boolean {
        var added = false
        for (element in elements) {
            if (add(element)) {
                added = true
            }
        }
        return added
    }

    override fun removeAll(elements: Collection<T>): Boolean {
        var removed = false
        for (element in elements) {
            while (remove(element)) {
                removed = true
            }
        }
        return removed
    }

    override fun retainAll(elements: Collection<T>): Boolean {
        val retainedElements = this.filter { it in elements }
        if (retainedElements.size != currentSize) {
            clear()
            for (element in retainedElements) {
                add(element)
            }
            return true
        }
        return false
    }

    override fun clear() {
        list.fill(null)
        front = 0
        rear = -1
        currentSize = 0
    }

    override fun toString(): String {
        return if (isEmpty()) {
            "[]"
        } else {
            val result = StringBuilder("[")
            var i = front
            repeat(currentSize) {
                result.append(list[i].toString())
                if (it != currentSize - 1) {
                    result.append(", ")
                }
                i = (i + 1) % capacity
            }
            result.append("]")
            result.toString()
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is CircularQueue<*>) return false

        if (currentSize != other.currentSize) return false

        for (i in 0 until currentSize) {
            if (list[(front + i) % capacity] != other.list[(other.front + i) % other.capacity]) {
                return false
            }
        }

        return true
    }

    override fun hashCode(): Int {
        var result = currentSize
        for (i in 0 until currentSize) {
            result = 31 * result + (list[(front + i) % capacity]?.hashCode() ?: 0)
        }
        return result
    }
}

fun <T> circularQueueOf(capacity: Int, vararg elements: T): CircularQueue<T> {
    val queue = CircularQueue<T>(capacity)
    for (element in elements) {
        queue.add(element)
    }
    return queue
}
