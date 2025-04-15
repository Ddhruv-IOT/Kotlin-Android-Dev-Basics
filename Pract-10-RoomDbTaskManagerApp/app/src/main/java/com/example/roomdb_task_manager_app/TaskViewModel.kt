package com.example.roomdb_task_manager_app

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

import com.example.roomdb_task_manager_app.Task
import com.example.roomdb_task_manager_app.TaskRepository

class TaskViewModel(application: Application) : AndroidViewModel(application) {
    private val db = TaskDatabase.getDatabase(application)
    private val repository = TaskRepository(db.taskDao())

    val tasks = repository.allTasks.asLiveData()

    fun addTask(task: Task) = viewModelScope.launch {
        repository.insert(task)
    }

    fun updateTask(task: Task) = viewModelScope.launch {
        repository.update(task)
    }
}
