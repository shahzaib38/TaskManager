package com.example.todolisttask.model


data class TaskState constructor( val data : List<Task>,
                                   val  isLoading :Boolean = false)