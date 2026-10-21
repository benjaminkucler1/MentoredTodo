package me.benjamin.mentoredtodo.ui

sealed interface Screen{
    data object List: Screen
    data class Details(val taskId: String): Screen
}

