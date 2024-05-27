package com.caesar84mx

import io.mockk.clearMocks
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class CircularQueueTest {

    @Test
    fun `should add elements and maintain the correct order`() {
        val queue = CircularQueue<Int>(3)
        queue.add(1)
        queue.add(2)
        queue.add(3)
        assertEquals("[1, 2, 3]", queue.toString())
    }

    @Test
    fun `should shift elements when capacity is reached`() {
        val queue = CircularQueue<Int>(3)
        queue.add(1)
        queue.add(2)
        queue.add(3)
        queue.add(4)
        assertEquals("[2, 3, 4]", queue.toString())
    }

    @Test
    fun `should remove elements correctly`() {
        val queue = CircularQueue<Int>(3)
        queue.add(1)
        queue.add(2)
        queue.add(3)
        assertTrue(queue.remove(2))
        assertEquals("[1, 3]", queue.toString())
    }

    @Test
    fun `should return the correct size`() {
        val queue = CircularQueue<Int>(3)
        queue.add(1)
        queue.add(2)
        assertEquals(2, queue.size)
    }

    @Test
    fun `should clear all elements`() {
        val queue = CircularQueue<Int>(3)
        queue.add(1)
        queue.add(2)
        queue.clear()
        assertTrue(queue.isEmpty())
    }

    @Test
    fun `should compare equality of two queues`() {
        val queue1 = circularQueueOf(
            capacity = 3,
            elements = arrayOf(
                "DISCONNECTED",
                "CONNECTING",
                "DISCONNECTED",
            )
        )

        val queue2 = circularQueueOf(3, "DISCONNECTED")
        queue2.add("CONNECTING")
        queue2.add("DISCONNECTED")

        assertEquals(queue1, queue2)
    }

    @Test
    fun `should correctly create a CircularQueue with initial elements`() {
        val queue = circularQueueOf(3, "DISCONNECTED", "CONNECTING")
        assertEquals("[DISCONNECTED, CONNECTING]", queue.toString())
    }

    @Test
    fun `should handle element containment checks`() {
        val queue = CircularQueue<Int>(3)
        queue.add(1)
        queue.add(2)
        assertTrue(queue.contains(1))
        assertFalse(queue.contains(3))
    }

    @Test
    fun `should correctly add all elements from a collection`() {
        val queue = CircularQueue<Int>(5)
        queue.addAll(listOf(1, 2, 3))
        assertEquals("[1, 2, 3]", queue.toString())
    }

    @Test
    fun `should correctly remove all elements from a collection`() {
        val queue = CircularQueue<Int>(5)
        queue.addAll(listOf(1, 2, 3, 4, 5))
        queue.removeAll(listOf(1, 3, 5))
        assertEquals("[2, 4]", queue.toString())
    }

    @Test
    fun `should correctly retain elements from a collection`() {
        val queue = CircularQueue<Int>(5)
        queue.addAll(listOf(1, 2, 3, 4, 5))
        queue.retainAll(listOf(2, 4))
        assertEquals("[2, 4]", queue.toString())
    }

    @Test
    fun `should correctly retain elements`() {
        val queue = CircularQueue<Int>(5)
        queue.addAll(listOf(1, 2, 3, 4, 5))
        queue.retainAll(listOf(2, 4))
        assertEquals("[2, 4]", queue.toString())
    }

    @Test
    fun `should return false for non-contained elements`() {
        val queue = CircularQueue<Int>(3)
        queue.add(1)
        queue.add(2)
        assertFalse(queue.contains(3))
    }

    @Test
    fun `should return true for contained elements`() {
        val queue = CircularQueue<Int>(3)
        queue.add(1)
        queue.add(2)
        assertTrue(queue.contains(2))
    }

    @Test
    fun `should iterate over elements in the correct order`() {
        val queue = CircularQueue<Int>(3)
        queue.add(1)
        queue.add(2)
        queue.add(3)
        val iter = queue.iterator()
        assertTrue(iter.hasNext())
        assertEquals(1, iter.next())
        assertEquals(2, iter.next())
        assertEquals(3, iter.next())
        assertFalse(iter.hasNext())
    }

    @Test
    fun `should handle mockk operations`() {
        val queue = mockk<CircularQueue<Int>>()
        every { queue.add(1) } returns true
        every { queue.size } returns 1
        every { queue.toString() } returns "[1]"

        assertTrue(queue.add(1))
        assertEquals(1, queue.size)
        assertEquals("[1]", queue.toString())

        verify { queue.add(1) }
        verify { queue.size }
        verify { queue.toString() }

        clearMocks(queue)
    }
}
