package com.example.tasksapplication.models

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity
data class Task(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val label: String,
    var isDone: Boolean = false,
    val categories: List<String> = listOf("house", "garden")
) {
    //var isDone by mutableStateOf(initialIsDone)
    override fun equals(other: Any?): Boolean {
        if(this === other) return true
        if (other !is Task) return false

        if(id != other.id) return false
        if(label != other.label) return false
        if(isDone != other.isDone) return false
        if(categories != other.categories) return false

        return true
    }
}

fun getTasks(): List<Task> {
    return listOf(
        Task(label = "read a book", isDone = false),
        Task(label = "learn mad", isDone = false),
        Task(label = "water the flowers", isDone = true)
    )
}
