package com.example.todolisttask.repo

import com.example.todolisttask.model.Task
import com.example.todolisttask.database.TaskDb
import javax.inject.Inject

class TaskRepository @Inject constructor(
    private val taskDb : TaskDb
) : BaseRepository(){



    val tasks =  taskDb.taskDao().getTasks()

    fun addTask(task: Task){
        taskDb.taskDao().addTask(task)
    }

    fun remove(task: Task) {
        taskDb.taskDao().removeAt(task) }


}