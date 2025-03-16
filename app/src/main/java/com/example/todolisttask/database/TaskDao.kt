package com.example.todolisttask.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.todolisttask.model.Task
import kotlinx.coroutines.flow.Flow


@Dao
interface TaskDao{


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addTask(task: Task)

    @Query("select * from task order by dueDate desc ")
    fun getTasks() : Flow<List<Task>>


    @Delete
    fun removeAt(task: Task)


}