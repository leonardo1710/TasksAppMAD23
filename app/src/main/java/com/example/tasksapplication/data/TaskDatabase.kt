package com.example.tasksapplication.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.tasksapplication.models.Task
import com.example.tasksapplication.utils.CustomConverters

@Database(
    entities = [Task::class],
    version = 2,
    exportSchema = false
)
@TypeConverters(CustomConverters::class)
abstract class TaskDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao

    companion object{
        @Volatile
        private var Instance: TaskDatabase? = null

        fun getDatabse(context: Context): TaskDatabase {
            return Instance?: synchronized(this){
                Room.databaseBuilder(context, TaskDatabase::class.java, "task_db")
                    .fallbackToDestructiveMigration()
                    .build()
                    .also {
                        Instance = it
                    }
            }
        }
    }
}