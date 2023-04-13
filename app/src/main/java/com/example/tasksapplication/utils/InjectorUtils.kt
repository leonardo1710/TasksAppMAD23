package com.example.tasksapplication.utils

import android.content.Context
import com.example.tasksapplication.TaskViewModelFactory
import com.example.tasksapplication.data.TaskDatabase
import com.example.tasksapplication.repositories.TaskRepository

object InjectorUtils {
    private fun getTaskRepository(context: Context): TaskRepository{
        return TaskRepository(TaskDatabase.getDatabse(context).taskDao())
    }

    fun provideTaskViewModelFactory(context: Context): TaskViewModelFactory {
        val repository = getTaskRepository(context)
        return TaskViewModelFactory(repository)
    }
}