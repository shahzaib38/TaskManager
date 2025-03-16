package com.example.todolisttask.navigaiton

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.navigation.compose.NavHost
import com.example.todolisttask.viewmodel.TaskViewModel


@Composable
fun SetupNavGraph(taskNavigation: TaskNavigation,
                  taskViewModel: TaskViewModel){

    val currentRoute by  rememberUpdatedState(taskNavigation.currentRoute)

    NavHost(taskNavigation.navController,
            startDestination = routeToTopLevel[currentRoute]?: currentRoute){

        addTaskNavigationBuilder(taskViewModel = taskViewModel,
            taskNavigation = taskNavigation)

        addSettingsNavigationBuilder(taskViewModel = taskViewModel)

    }

}