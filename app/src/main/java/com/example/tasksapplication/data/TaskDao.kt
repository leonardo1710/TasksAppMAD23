package com.example.tasksapplication.data

import androidx.room.*
import com.example.tasksapplication.models.Task
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {
    // CRUD
    @Insert
    suspend fun add(task: Task)

    @Update
    suspend fun update(task: Task)

    @Delete
    suspend fun delete(task: Task)

    @Query("SELECT * from task")
    fun readAll(): Flow<List<Task>>

    @Query("Select * from task where isDone = 1")
    fun readAllChecked(): Flow<List<Task>>

    @Query("Select * from task where id=:taskId")
    fun getTaskById(taskId: Int): Task

    @Query("DELETE from task")
    fun deleteAll()

}