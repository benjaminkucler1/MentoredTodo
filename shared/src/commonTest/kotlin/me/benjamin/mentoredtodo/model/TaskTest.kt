package me.benjamin.mentoredtodo.model

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse


class TaskTest {
    @Test
    fun taskCreatedAndPrinted() {
        val t = Task(id = "1", title = "TestTask", isCompleted = false)
        println(t)
    }
    @Test
    fun copyingTaskWithCompletedStatusDoesNotModifyOriginal(){
        val t = Task("2", "Task Title", "New task", false)
        val tCompleted = t.copy(isCompleted = true)

        assertFalse(t.isCompleted)
        assertFalse(tCompleted.isCompleted)
        assertEquals(t.id, tCompleted.id)
        assertEquals(t.title, tCompleted.title)
    }
}