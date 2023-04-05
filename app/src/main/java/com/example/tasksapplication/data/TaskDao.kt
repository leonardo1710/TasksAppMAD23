package com.example.tasksapplication.data

import androidx.room.*
import com.example.tasksapplication.models.Task
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {
    @Insert
    fun add(task: Task)

    @Delete
    fun delete(task: Task)

    @Update
    fun update(task: Task)

    @Query("Select * from task")
    fun readAll(): Flow<List<Task>>

    @Query("Select * from task where isDone = 1")
    fun readAllDone(): Flow<List<Task>>

    @Query("Select * from taks where id=:id")
    fun getById(id: Int): Task

    @Query("Delete from task")
    fun deleteAll()

}