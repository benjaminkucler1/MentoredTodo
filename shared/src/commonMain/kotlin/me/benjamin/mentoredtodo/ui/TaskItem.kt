package me.benjamin.mentoredtodo.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Checkbox
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import me.benjamin.mentoredtodo.model.Task

@Composable
fun Task(task: Task){
    Column {
        Row {
            Checkbox(checked = task.isCompleted, onCheckedChange = null)
            Column {
                Text(text = task.title, fontWeight = FontWeight.Bold)
                if(task.description != null) {
                    Text(text = task.description)
                }
            }
        }
        HorizontalDivider()
    }
}