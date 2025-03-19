package com.example.todolisttask.screens

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.todolisttask.common.GsonUtils
import com.example.todolisttask.dialog.TaskDialogManager
import com.example.todolisttask.ext.BountyFAB
import com.example.todolisttask.ext.EmptyListView
import com.example.todolisttask.ext.QuickAddTask
import com.example.todolisttask.menu.TaskMenuOption
import com.example.todolisttask.model.DialogType
import com.example.todolisttask.model.Task
import com.example.todolisttask.navigaiton.Settings
import com.example.todolisttask.navigaiton.TaskCreation
import com.example.todolisttask.navigaiton.TaskNavigation
import com.example.todolisttask.navigaiton.TaskDetails
import com.example.todolisttask.shimmer.ShimmerEffect
import com.example.todolisttask.systemUi.ManageSystemBars
import com.example.todolisttask.topbar.TopBar
import com.example.todolisttask.viewmodel.TaskViewModel
import kotlinx.coroutines.launch


@Composable
    fun  TaskList(taskViewModel : TaskViewModel,
                  taskNavigation: TaskNavigation) {

        val onMenuClick = remember {{taskViewModel.setDialog(DialogType.TaskMenu)}}
        val optionMenu = remember<(TaskMenuOption)->Unit> { {taskMenuOption->

            when(taskMenuOption){
                TaskMenuOption.SORT_BY -> {
                    taskViewModel.setDialog(DialogType.SortMenu) }

                TaskMenuOption.SETTINGS -> {
                    taskNavigation.navigate(Settings.basePath)
                    taskViewModel.setDialog(DialogType.None) }
            } } }


          val filterClick = remember { { taskViewModel.setDialog(DialogType.Filter) } }
          val navigateToCreation = remember { { taskNavigation.navigate(TaskCreation(Task()).createRoute()) }}
          val quickNavigate = remember<(String)->Unit > { { title -> taskNavigation.navigate(TaskCreation(Task(title = title)).createRoute()) } }
          val snackbarHostState = remember { SnackbarHostState() }
          val coroutineScope = rememberCoroutineScope()



    val navigateToDetails = remember<(Task)->Unit > {{ task ->
        taskNavigation.navigate(TaskDetails(task).createRoute()) } }


        ManageSystemBars( barColor = Color.White,
                          isVisible = true)



    LaunchedEffect(Unit) {
        taskViewModel.snackbarListFlow.collect { message ->
            coroutineScope.launch {

               val snackbarResult = snackbarHostState.showSnackbar(
                    message = "Task deleted",
                    actionLabel = "Undo",
                    duration = SnackbarDuration.Short
                )


                when(snackbarResult){
                    SnackbarResult.Dismissed -> {}
                    SnackbarResult.ActionPerformed -> {
                        taskViewModel.restoreDeletedTask()

                    }
                }
            }
        }
    }


    Box(modifier = Modifier.fillMaxSize()) {
            Scaffold(
                snackbarHost = { SnackbarHost(snackbarHostState) }
                ,topBar = {

                TopBar(onMenuClick = onMenuClick, filterClick = filterClick)


                              },
                bottomBar = { QuickAddTask(quickNavigate) },
                floatingActionButton = {

                    BountyFAB(navigateToTaskCreation = navigateToCreation ,
                        onClick = navigateToCreation)


                }) { contentPadding ->
                Box(modifier = Modifier.fillMaxSize()
                        .padding(contentPadding)
                        .imePadding()) {
                    TaskScreen(taskViewModel,
                        onClick = navigateToDetails
                        ) } }

            TaskDialogManager(taskViewModel = taskViewModel,
                onOptionMenu = optionMenu)

        }


    }

@Composable
fun TaskScreen(taskViewModel: TaskViewModel ,
               modifier : Modifier = Modifier.fillMaxWidth(),
               onClick : (Task)->Unit
               ){

    val taskState by taskViewModel.tasks.collectAsStateWithLifecycle()

    Log.i("TaskList" , "Recomposed")

    Box(modifier  = modifier ) {
        when {
            taskState.isLoading -> ShimmerEffect()
            taskState.data.isNotEmpty() -> TaskTimelineView(
                taskState = taskState,
                removeItem = taskViewModel::removeTask,
                sortItems = taskViewModel.sortItems,
                filterItems = taskViewModel.filterItems,
                onClick = onClick,
               deleteItems =  taskViewModel.deletedTaskState
                )

            else -> EmptyListView()
        }
    }
}