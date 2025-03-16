package com.example.todolisttask.navigaiton

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.todolisttask.navigaiton.NavCommon.SETTINGS_TOP
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.todolisttask.screens.SettingsScreen
import com.example.todolisttask.viewmodel.TaskViewModel


fun NavGraphBuilder.addSettingsNavigationBuilder(taskViewModel: TaskViewModel,
                                                 ){


    navigation(
        startDestination = TaskList.basePath,
        route = SETTINGS_TOP){

        composable(route = Settings.basePath){

            SettingsScreen(taskViewModel)

        }


    }

}