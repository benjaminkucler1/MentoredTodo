package me.benjamin.mentoredtodo.model

data class Task(
    val id: String,
    val title: String,
    val description: String? = null,
    val isCompleted: Boolean = false,
)