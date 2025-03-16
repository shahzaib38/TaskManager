package com.example.todolisttask.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.todolisttask.model.PriorityConverter
import com.example.todolisttask.model.Task


@Database(
    entities = [Task::class],
    version = 1,
    exportSchema = false)
@TypeConverters(PriorityConverter::class)
abstract class TaskDb : RoomDatabase() {

    companion object {

        @Volatile
        private var INSTANCE: TaskDb? = null

        fun getDatabase(context: Context): TaskDb {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TaskDb::class.java,
                    "task_database"
                ).build()
                INSTANCE = instance
                instance
            }
        } }

    abstract fun taskDao(): TaskDao

}