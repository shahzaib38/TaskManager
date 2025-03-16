package com.example.todolisttask.navigaiton

import androidx.navigation.NavType
import androidx.navigation.navArgument

object NavCommon {
    const val TOP_ROUTE = "Task"
    const val TASK_LIST = "TaskList"
    const val TASK_VIEW = "TaskView"



   const val SETTINGS_TOP = "SettingsTop"
    const val SETTINGS = "Settings"



    const val TITLE = "title"

    //


    fun listNavArgument() =  listOf(
        navArgument(TITLE){
            type = NavType.StringType
            defaultValue = "" })




    //
}