package com.example.roomdb_task_manager_app

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tasks")
data class Task(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val description: String,
    val estimatedTime: String,
    val isCompleted: Boolean = false
)