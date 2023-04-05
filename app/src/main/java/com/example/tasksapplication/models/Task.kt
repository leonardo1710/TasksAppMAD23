package com.example.tasksapplication.models

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity
data class Task(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val label: String,
    var isDone: Boolean = false
) {
    @Ignore
    val initialIsDone: Boolean = false
}

fun getTasks(): List<Task> {
    return listOf(
        Task(label = "read a book"),
        Task( label = "learn mad"),
        Task( label = "water the flowers")
    )
}
