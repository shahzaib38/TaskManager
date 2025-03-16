package com.example.todolisttask.ext

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.gestures.detectDragGesturesAfterLongPress
import androidx.compose.foundation.layout.offset
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.example.todolisttask.model.Task
import kotlinx.coroutines.delay
import java.util.Collections
import kotlin.math.roundToInt


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskItemWithSwipe(mappedData : SnapshotStateList<Task>,
                      index :Int,
                      onClick:(Task)->Unit,
                      removeAt:(Task,Int )->Unit,
                      isDragging : Boolean,
                      deleteItems :  MutableState<Pair<Task, Int>?> ){





    val elevation = animateDpAsState(if (isDragging) 16.dp else 0.dp)
    val isCompleted = remember { mutableStateOf(false ) }
    var isRemoved by  remember { mutableStateOf(false ) }
    val removeClick = remember { { isRemoved = true } }



    // Restrict End-to-Start movement to 50%
    val swipeToDismissState =      rememberSwipeToDismissBoxState(
        confirmValueChange = {
            when(it) {
                SwipeToDismissBoxValue.Settled -> return@rememberSwipeToDismissBoxState true
                SwipeToDismissBoxValue.StartToEnd -> {
                    isRemoved = true }
                SwipeToDismissBoxValue.EndToStart -> {
                    isCompleted.value = true

                }
            }
            return@rememberSwipeToDismissBoxState true
        }, positionalThreshold = { it * 0.25f })


//
    LaunchedEffect(deleteItems) {
        if (deleteItems.value != null && index == deleteItems.value?.second) {
            swipeToDismissState.snapTo(SwipeToDismissBoxValue.Settled)
            isRemoved = false
            isCompleted.value = false
            deleteItems.value = null
        }
    }


    LaunchedEffect(isRemoved) {
        if (isRemoved) {
            delay(500) // Wait for animation completion
            removeAt(mappedData.removeAt(index = index),index) } }





    AnimatedVisibility(
        visible = !isRemoved,
        exit = shrinkVertically(
            shrinkTowards = Alignment.Bottom, // Shrink upwards
            animationSpec = tween(durationMillis = 500)
        ) + fadeOut(animationSpec = tween(durationMillis = 500))) {

        SwipeToDismissBox(
            modifier = Modifier.shadow(elevation.value),
            enableDismissFromEndToStart = true,
           enableDismissFromStartToEnd = true,
            state = swipeToDismissState,
            backgroundContent = { DismissBackground(swipeToDismissState,
                isCompleted,
                remove = removeClick ) }) {
            TaskItem(
                task = mappedData[index],
                onItemClick = onClick) }

    }

}


