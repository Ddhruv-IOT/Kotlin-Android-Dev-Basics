package com.example.roomdb_task_manager_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import com.example.roomdb_task_manager_app.ui.theme.RoomDbTaskManagerAppTheme
import androidx.lifecycle.viewmodel.compose.viewModel


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val viewModel: TaskViewModel = viewModel(factory = ViewModelProvider.AndroidViewModelFactory(application))
            MaterialTheme {
                TaskApp(viewModel)
            }
        }
    }
}


@Composable
fun TaskApp(viewModel: TaskViewModel) {
    val tasks by viewModel.tasks.observeAsState(emptyList())
    var showDialog by remember { mutableStateOf(false) }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { showDialog = true }) {
                Icon(Icons.Default.Add, contentDescription = "Add Task")
            }
        }
    ) {
        Column(Modifier.padding(it)) {
            tasks.forEach { task ->
                TaskItem(task = task, onToggle = {
                    viewModel.updateTask(task.copy(isCompleted = !task.isCompleted))
                })
            }
        }
        if (showDialog) {
            AddTaskDialog(onDismiss = { showDialog = false }) { task ->
                viewModel.addTask(task)
                showDialog = false
            }
        }
    }
}