package com.example.tasksapplication.models

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

data class Task(
    val label: String,
    val initialIsDone: Boolean = false
) {
    var isDone by mutableStateOf(initialIsDone)
}

fun getTasks(): List<Task> {
    return listOf(
        Task("read a book", false),
        Task( "learn mad", false),
        Task( "water the flowers", true)
    )
}
