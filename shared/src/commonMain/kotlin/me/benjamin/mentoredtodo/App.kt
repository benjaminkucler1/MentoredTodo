package me.benjamin.mentoredtodo

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import me.benjamin.mentoredtodo.model.Task
import me.benjamin.mentoredtodo.ui.Screen
import me.benjamin.mentoredtodo.ui.TaskDetailsScreen
import me.benjamin.mentoredtodo.ui.TaskListScreen

@Composable
@Preview
fun App() {

    var currentScreen by remember { mutableStateOf<Screen>(Screen.List) }

    val tasks = remember {
        mutableStateListOf(
            Task(id = "0", title = "Do your homework"),
            Task(id = "1", title = "Mow the law", isCompleted = true),
            Task(
                id = "2",
                title = "Do singing lessons",
                description = "mp3 file name is 'practice no. 3'"
            ),
            Task(id = "3", title = "Learn new language")
        )
    }

    var nextTaskId by remember {
        mutableIntStateOf((tasks.maxOfOrNull { it.id.toInt() } ?: -1) + 1)
    }

    fun generateNextId(): String = (nextTaskId++).toString()


    fun onTaskOpen(taskId: String) {
        currentScreen = Screen.Details(taskId)
    }

    fun onBack() {
        currentScreen = Screen.List
    }

    MaterialTheme {
        when (val screen = currentScreen) {
            is Screen.Details -> {
                val task = tasks.find { it.id == screen.taskId }
                if (task != null) TaskDetailsScreen(task = task, onBack = ::onBack)
                else Column {
                    Text(text = "Task cannot be found!")
                    Button(onClick = ::onBack) {
                        Text(text = "back")
                    }
                }
            }

            Screen.List -> TaskListScreen(
                tasks = tasks,
                getNextId = ::generateNextId,
                onTaskOpen = ::onTaskOpen
            )
        }

    }
}