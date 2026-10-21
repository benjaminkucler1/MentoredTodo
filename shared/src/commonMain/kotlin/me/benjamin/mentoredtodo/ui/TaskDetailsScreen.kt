package me.benjamin.mentoredtodo.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import me.benjamin.mentoredtodo.model.Task

@Composable
@Preview
fun TaskDetailsScreenPreview1(){
    val exampleTask = Task("1", "Mow the lawn", "Try to do it before 5pm", false)

    MaterialTheme{
        TaskDetailsScreen(exampleTask) {}
    }
}

@Composable
@Preview
fun TaskDetailsScreenPreview2(){
    val exampleTask = Task(id = "1", title = "Mow the lawn", isCompleted = true)

    MaterialTheme{
        TaskDetailsScreen(exampleTask) {}
    }
}

@Composable
fun TaskDetailsScreen(task: Task, onBack: () -> Unit){
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "Task details:", style = MaterialTheme.typography.headlineSmall)
            HorizontalDivider(Modifier.padding(bottom = 10.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(task.isCompleted, onCheckedChange = null)
                Text(text = buildAnnotatedString {
                    withStyle(SpanStyle(fontWeight = FontWeight.Bold)) {
                        append(task.title)
                    }
                    append(" (${task.id})")
                })
            }
            if (task.description != null) Text(
                text = task.description,
                style = MaterialTheme.typography.bodySmall
            )
        Button(
            onClick = onBack
        ) {
            Text(text = "back")
        }
    }
}