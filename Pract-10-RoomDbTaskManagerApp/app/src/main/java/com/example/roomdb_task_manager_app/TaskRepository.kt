package com.example.roomdb_task_manager_app

import kotlinx.coroutines.flow.Flow

import com.example.roomdb_task_manager_app.Task
import com.example.roomdb_task_manager_app.TaskDao

class TaskRepository(private val dao: TaskDao) {
    val allTasks = dao.getAllTasks()
    suspend fun insert(task: Task) = dao.insertTask(task)
    suspend fun update(task: Task) = dao.updateTask(task)
}
