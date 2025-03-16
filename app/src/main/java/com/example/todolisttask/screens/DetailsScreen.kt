package com.example.todolisttask.screens

import androidx.compose.runtime.Composable
import com.example.todolisttask.model.Task
import com.example.todolisttask.navigaiton.TaskNavigation
import com.example.todolisttask.viewmodel.TaskViewModel


@Composable
fun DetailsScreen(task :Task,
                  taskViewModel: TaskViewModel,
                  taskNavigation : TaskNavigation){


    TaskCreation(
        task  = task,
        taskNavigation = taskNavigation,
        taskViewModel = taskViewModel)

}