package com.example.roomdb_task_manager_app


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.roomdb_task_manager_app.Task

//@Composable
//fun TaskItem(
//    task: Task,
//    onToggle: (Task) -> Unit
////    onToggleComplete: (Task) -> Unit
//) {
//    Card(
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(vertical = 4.dp, horizontal = 8.dp),
//        colors = CardDefaults.cardColors(
//            containerColor = if (task.isCompleted) Color(0xFFD3E4CD) else Color(0xFFFFF6C3)
//        )
//    ) {
//        Column(
//            modifier = Modifier
//                .padding(16.dp)
//        ) {
//            Text(text = task.name, style = MaterialTheme.typography.titleMedium)
//            Text(text = task.description, style = MaterialTheme.typography.bodyMedium)
//            Text(text = "Estimated time: ${task.estimatedTime}", style = MaterialTheme.typography.bodySmall)
//
//            Spacer(modifier = Modifier.height(8.dp))
//
//            Button(
//                onClick = {
//                    val updatedTask = task.copy(isCompleted = !task.isCompleted)
//                    onToggleComplete(updatedTask)
//                }
//            ) {
//                Text(if (task.isCompleted) "Mark as Incomplete" else "Mark as Complete")
//            }
//        }
//    }
//}


@Composable
fun TaskItem(
    task: Task,
    onToggle: (Task) -> Unit // This is the new onToggle parameter
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp, horizontal = 8.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (task.isCompleted) Color(0xFFD3E4CD) else Color(0xFFFFF6C3)
        )
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            Text(text = task.name, style = MaterialTheme.typography.titleMedium)
            Text(text = task.description, style = MaterialTheme.typography.bodyMedium)
            Text(text = "Estimated time: ${task.estimatedTime}", style = MaterialTheme.typography.bodySmall)

            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = {
                    onToggle(task) // Call the onToggle function when the button is clicked
                }
            ) {
                Text(if (task.isCompleted) "Mark as Incomplete" else "Mark as Complete")
            }
        }
    }
}