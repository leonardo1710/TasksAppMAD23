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
    @Ignore
    val initialIsDone: Boolean = false,
    var isDone: Boolean
) {
    //var isDone by mutableStateOf(initialIsDone)
}

fun getTasks(): List<Task> {
    return listOf(
        Task(label = "read a book", isDone = false),
        Task( label = "learn mad", isDone = false),
        Task( label = "water the flowers", isDone = true)
    )
}
