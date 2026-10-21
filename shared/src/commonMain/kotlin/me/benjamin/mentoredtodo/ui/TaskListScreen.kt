package me.benjamin.mentoredtodo.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import me.benjamin.mentoredtodo.model.Task

@Composable
fun TaskListScreen(tasks: SnapshotStateList<Task>, getNextId: () -> String, onTaskOpen: (String) -> Unit) {

    var selectedTaskId by remember { mutableStateOf<String?>(null) }
    var editingTitle by remember { mutableStateOf("") }

    var newTaskTitle by remember { mutableStateOf("") }

    fun getTaskTitleOrNullById(id: String?): String?{
        return tasks.find { it.id == id }?.title
    }

    fun cancelTaskEditing(){
        editingTitle = ""
        selectedTaskId = null
    }

    fun setTaskCompleted(task: Task, newCheckBoxValue: Boolean){
        val taskIndex = tasks.indexOfFirst { it.id == task.id }
        if (taskIndex == -1) return
        val currentTask = tasks[taskIndex]
        tasks[taskIndex] = currentTask.copy(isCompleted = newCheckBoxValue)
    }

    fun addTask(newTitle: String){
        if (newTitle.isBlank()) return
        val newTask = Task(id=getNextId(), title = newTitle.trim())
        tasks.add(newTask)
        newTaskTitle = ""
    }

    fun deleteTask(task: Task){
        if (task.id == selectedTaskId) cancelTaskEditing()
        tasks.removeAll { it.id == task.id }
    }

    fun selectTaskForEditing(id: String){
        if (id == selectedTaskId){
            cancelTaskEditing()
            return
        }
        selectedTaskId = id
        editingTitle = getTaskTitleOrNullById(id) ?: ""
    }

    fun saveTaskEdit(id: String, newTitle: String){
        if (newTitle.isBlank()) return

        val taskIndex = tasks.indexOfFirst { it.id == id }
        if (taskIndex == -1) return

        val newTask = tasks[taskIndex].copy(title = newTitle.trim())
        tasks[taskIndex] = newTask
        cancelTaskEditing()
    }

    Column {
        Row(modifier = Modifier.fillMaxWidth().padding(all = 10.dp), horizontalArrangement = Arrangement.SpaceBetween) {
            Text(text = "My tasks", fontWeight = FontWeight.Bold)
            Text(text = "${tasks.count { it.isCompleted }} of ${tasks.size} tasks completed")
        }
        HorizontalDivider(modifier = Modifier.padding(bottom = 10.dp))

        Row {
            TextField(value = newTaskTitle,
                onValueChange = { newTaskTitle = it},
                label = {Text(text="Title")},
                placeholder = {Text(text="Enter Task Title")}
            )
            Button(
                onClick = { addTask(newTaskTitle) },

            ){
                Text(text = "Add Task")
            }
        }
        HorizontalDivider(modifier = Modifier.padding(bottom = 10.dp))

        tasks.forEach { task ->
            TaskItem(
                task,
                onTaskToggle = {newCheckboxStatus -> setTaskCompleted(task, newCheckboxStatus)},
                onTaskEdit = {selectTaskForEditing(task.id)},
                onTaskDeleted = {deleteTask(task)},
                onTaskOpen = {onTaskOpen(task.id)}
            )
            if(task.id == selectedTaskId){
                Row (verticalAlignment = Alignment.CenterVertically) {
                    TextField(
                        value = editingTitle,
                        onValueChange = { editingTitle = it },
                    )
                    Button(
                        onClick = { saveTaskEdit(task.id, editingTitle) },
                        enabled = editingTitle.isNotBlank() && editingTitle.trim() != task.title
                    )
                    {
                        Text(text="Save")
                    }
                    Button(onClick = { cancelTaskEditing() }){
                        Text(text="Cancel")
                    }
                }
            }

            HorizontalDivider()
        }
        if (tasks.isEmpty()){
            Text(text = "List is empty")
        }
    }
}