package com.example.todolisttask.navigaiton

import android.util.Log
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.ui.graphics.TransformOrigin
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.todolisttask.common.GsonUtils
import com.example.todolisttask.model.Task
import com.example.todolisttask.navigaiton.NavCommon.TOP_ROUTE
import com.example.todolisttask.screens.DetailsScreen
import com.example.todolisttask.screens.TaskCreation
import com.example.todolisttask.screens.TaskList
import com.example.todolisttask.viewmodel.TaskViewModel


fun NavGraphBuilder.addTaskNavigationBuilder(taskViewModel: TaskViewModel,
                                             taskNavigation :TaskNavigation){


    navigation(
        startDestination = TaskList.basePath,
        route = TOP_ROUTE){

        composable(route = TaskList.basePath,
//            enterTransition = {
//
//              //  Log.i("TaskNavigationBuilder" , "Screen one Enter Transition")
//
////                scaleIn(
////                    animationSpec = tween(durationMillis = 500), // Set duration to 1000ms
////                    transformOrigin = TransformOrigin(0f, 1f) // Scale from the top-right corner
////                )
//            }
//
          //  ,
            exitTransition = {
                Log.i("TaskNavigationBuilder" , "Screen one Exit Transition")

                fadeOut(
                    animationSpec = tween(durationMillis = 1000),
                    targetAlpha = 0.99f
                )
            }

            ,
            popEnterTransition = {

                Log.i("TaskNavigationBuilder" , "Screen one pop enter Transition")

                fadeIn()


            },
            popExitTransition = null){
            TaskList(taskViewModel = taskViewModel,
                taskNavigation = taskNavigation)
        }


        composable(route = TaskCreation().basePath,
            arguments = NavCommon.listNavArgument(),

            enterTransition = {

                scaleIn(animationSpec = tween(durationMillis = 700),
                    transformOrigin = TransformOrigin(0f, 1f)) },
            exitTransition = null,
            popEnterTransition = null ,
            popExitTransition = {

                fadeOut(
                    animationSpec = tween(durationMillis = 500),
                    targetAlpha = 0.99f)
//

            }

//            {
//                Log.i("TaskNavigationBuilder" , "Screen two  pop exit  Transition")
//
//                fadeOut(
//                    animationSpec = tween(durationMillis = 1000),
//                    targetAlpha = 0.99f)
//
//            }
            ){ navBackStackEntry ->



            val task =   GsonUtils.fromJson<Task>(navBackStackEntry.arguments?.getString("task"))?:Task()

            TaskCreation(
                task  = task,
                taskNavigation = taskNavigation,
                taskViewModel = taskViewModel)

        }

        composable(route = TaskDetails().basePath){ navBackStackEntry ->


            val task =   GsonUtils.fromJson<Task>(navBackStackEntry.arguments?.getString("task"))?:Task()
            DetailsScreen(task = task,
                taskNavigation = taskNavigation,
                taskViewModel = taskViewModel)




        }





    }



}