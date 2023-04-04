package com.example.tasksapplication

import androidx.lifecycle.ViewModel
import com.example.tasksapplication.models.Task
import com.example.tasksapplication.models.getTasks
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class TaskViewModel: ViewModel() {
    private val _tasks = MutableStateFlow(getTasks())
    val tasks: StateFlow<List<Task>> = _tasks.asStateFlow()

    fun toggleDoneState(task: Task) = _tasks.value.find { it.label == task.label }?.let {
        it.isDone = !it.isDone
    }

    fun addTask(task: Task){
        _tasks.update {
            val list: MutableList<Task> = _tasks.value.toMutableList()
            list.add(task)
            list
        }
    }

    fun deleteTask(task: Task) {
        _tasks.update {
            val list: MutableList<Task> = _tasks.value.toMutableList()
            list.remove(task)
            list
        }
    }
}