package me.benjamin.mentoredtodo.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Checkbox
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import me.benjamin.mentoredtodo.model.Task

@Composable
fun TaskItem(task: Task, onTaskToggle: (Boolean) -> Unit, onTaskEdit: () -> Unit, onTaskDeleted: () -> Unit, onTaskOpen: (String) -> Unit){
        Column{
            Row {
                Checkbox(checked = task.isCompleted, onCheckedChange = onTaskToggle)
                Column {
                    Text(
                        text = task.title,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.clickable {onTaskOpen(task.id)}
                    )
                    if (task.description != null) {
                        Text(text = task.description)
                    }
                }
                IconButton(onClick = {onTaskEdit()}){
                    Text(text="✏\uFE0F")
                }
                IconButton(onClick = {onTaskDeleted()}){
                    Text(text="❌")
                }
            }
        }
}