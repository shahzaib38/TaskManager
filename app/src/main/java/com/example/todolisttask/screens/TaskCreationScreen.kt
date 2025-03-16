package com.example.todolisttask.screens

import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.todolisttask.custom.CustomProgressBar
import com.example.todolisttask.dialog.TaskDialogManager
import com.example.todolisttask.ext.BountyFAB
import com.example.todolisttask.ext.CompletionView
import com.example.todolisttask.ext.DateView
import com.example.todolisttask.ext.DescriptionView
import com.example.todolisttask.ext.Priority
import com.example.todolisttask.ext.PrioritySelection
import com.example.todolisttask.ext.TaskProgress
import com.example.todolisttask.ext.TaskTop
import com.example.todolisttask.model.DialogType
import com.example.todolisttask.model.Task
import com.example.todolisttask.modifier.circularReveal
import com.example.todolisttask.navigaiton.TaskNavigation
import com.example.todolisttask.systemUi.ManageStatusBar
import com.example.todolisttask.viewmodel.TaskViewModel
import kotlinx.coroutines.launch
import kotlin.math.max
import kotlin.math.sqrt


fun calculateDiagonalDistance(x: Float, y: Float, width: Float, height: Float): Float {
    val dx = max(x, 1 - x) * width
    val dy = max(y, 1 - y) * height
    return sqrt(dx * dx + dy * dy) }


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskCreation(task : Task,
                 color :Color = MaterialTheme.colorScheme.primary,
                 taskNavigation: TaskNavigation,
                 taskViewModel: TaskViewModel) {


    LaunchedEffect(task) {
        taskViewModel.apply {
            updateTitle(task.title)
            this.updateId(task.id)
            this.updateDescription(task.description)
            this.setPriority(task.priority)
            this.setDateTime(task.dueDate,0)
            this.onComplete(task.isCompleted )

        }
    }

    val dateChange = remember { {taskViewModel.setDialog(DialogType.DatePicker) }}
    var isExiting by remember { mutableStateOf(false) }

    BackHandler(
        enabled = !isExiting){
        isExiting = true }



    val onBackClick = remember { { isExiting = true  } }


    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        taskViewModel.snackbarFlow.collect { message ->
            coroutineScope.launch {
                snackbarHostState.showSnackbar(message)
            }
        }
    }


    val transitionProgress = remember { androidx.compose.animation.core.Animatable(0f) }

    LaunchedEffect(Unit) {
        transitionProgress.animateTo(
            targetValue = 1f,
            animationSpec = tween(durationMillis = 700, easing = LinearEasing))
    }


    LaunchedEffect(isExiting) {
        if (isExiting) {

            taskNavigation.navigateUp()

            transitionProgress.animateTo(
                targetValue = 0f,
                animationSpec = tween(
                    durationMillis = 700,
                    easing = LinearEasing))

        }
    }


    val addTask  = remember { {
        if(taskViewModel.addTask()){

            isExiting = true
            taskNavigation.popBackStack()
        }} }


    val scrollState = rememberScrollState()


    ManageStatusBar(isVisible = false, color = color)

    Scaffold(modifier = Modifier
        .background(Color.Red)
        .verticalScroll(scrollState).
    circularReveal( progress = transitionProgress ,
        points = Offset(0f,1f)),
        snackbarHost = { SnackbarHost(snackbarHostState) },

        floatingActionButton = {
            BountyFAB(
                navigateToTaskCreation = addTask,
                color = Color(android.graphics.Color.parseColor("#FFC107")),
                icon = Icons.Default.Check,
                onClick = addTask)


        }, topBar = {

            TopAppBar(


                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = color
                ),
                modifier = Modifier,
                title = {
                    Text(
                        "Task",
                        color = Color.White
                    )


                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.White
                        )
                    }
                }
            )

        }) { contentPadding ->



        Box(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier.fillMaxSize()
                    .padding(contentPadding),
                verticalArrangement = Arrangement.spacedBy(12.dp)) {

                TaskTop(
                    color = color,
                    onValueChange = taskViewModel::updateTitle,
                    task = taskViewModel.task
                )

                TaskContent(
                    task = taskViewModel.task,
                    onValueChanged = taskViewModel::updateDescription,
                    onPrioritySelected = taskViewModel::setPriority,
                    onTimeChange = dateChange,
                    onComplete = taskViewModel::onComplete)

            }

            TaskDialogManager(taskViewModel, onOptionMenu = {})
        }


    }
}


//


@Composable
fun TaskContent(onValueChanged:(String)->Unit, task:State<Task>,
                onPrioritySelected : (Priority)->Unit,
                onTimeChange:()->Unit,
                onComplete:(Boolean)->Unit ) {

     Column(
            modifier = Modifier
                .padding(horizontal = 12.dp)
                .wrapContentWidth().wrapContentHeight(),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.Start
        ) {


         TaskProgress(task)

         DescriptionView(
                onValueChanged = onValueChanged,
                task = task)



         PrioritySelection(
                task = task,
                onPrioritySelected = onPrioritySelected)



         DateView(
                onTimeChange = onTimeChange,
                task = task
            )


            CompletionView(
                task,
                onComplete = onComplete
            )


        }

}