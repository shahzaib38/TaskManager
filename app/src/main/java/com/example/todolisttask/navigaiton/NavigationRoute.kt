package com.example.todolisttask.navigaiton

import androidx.navigation.navArgument
import com.example.todolisttask.common.GsonUtils
import com.example.todolisttask.model.Task
import com.example.todolisttask.navigaiton.NavCommon.TASK_LIST
import com.example.todolisttask.navigaiton.NavCommon.TASK_VIEW
import com.example.todolisttask.navigaiton.NavCommon.TOP_ROUTE
import com.google.gson.Gson


sealed class Route constructor(val basePath : String,
                                         val  topLevelRoute : String )


object TaskList  : Route(basePath = TASK_LIST, topLevelRoute = TOP_ROUTE)

data class TaskCreation constructor(val taskDetails : Task =Task()  ) : Route(basePath ="TaskCreation/{task}"  , topLevelRoute = TOP_ROUTE){

    fun createRoute() = "TaskCreation/${GsonUtils.toJson(taskDetails)}"
}


data class TaskDetails constructor(val taskDetails : Task =Task() ) : Route(basePath = "TaskDetails/{task}"  , topLevelRoute = TOP_ROUTE){
    fun createRoute() = "TaskDetails/${GsonUtils.toJson(taskDetails)}"

}

object Settings : Route(NavCommon.SETTINGS,
    topLevelRoute = NavCommon.SETTINGS_TOP)

val routeToTopLevel: Map<String, String> = listOf(
    TaskList,
    TaskCreation(),
    TaskDetails()
).associate { it.basePath to it.topLevelRoute }