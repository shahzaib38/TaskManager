package com.example.todolisttask.navigaiton

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController


@Composable
fun rememberTaskNavController(
    navController : NavHostController = rememberNavController()
) : TaskNavigation {
    return remember(navController){ TaskNavigation(navController) } }

class TaskNavigation constructor( val navController  : NavHostController){

    val currentRoute =  navController.currentBackStackEntry?.destination?.route?: TaskList.basePath

    fun navigate(route : String ){
        if (route != navController.currentDestination?.route) {
            navController.navigate(route) } }

    fun popBackStack(){
        navController.popBackStack() }


    fun navigateUp(){
        navController.navigateUp() }

    fun navigateBottomNav(route: String) {
        if (route != navController.currentDestination?.route) {
            navController.navigate(route) {
                launchSingleTop = true
                restoreState = true
                popUpTo(navController.graph.findStartDestination().id) {
                    saveState = true

                }
            } } }


}
