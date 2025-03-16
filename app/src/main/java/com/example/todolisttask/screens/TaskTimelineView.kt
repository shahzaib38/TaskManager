package com.example.todolisttask.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback
import com.example.todolisttask.ext.TaskItemWithSwipe
import com.example.todolisttask.filter.Filter
import com.example.todolisttask.model.Task
import com.example.todolisttask.model.TaskState
import com.example.todolisttask.sort.Sort
import org.burnoutcrew.reorderable.ReorderableItem
import org.burnoutcrew.reorderable.detectReorderAfterLongPress
import org.burnoutcrew.reorderable.rememberReorderableLazyListState
import org.burnoutcrew.reorderable.reorderable

@Composable
fun TaskTimelineView(taskState : TaskState ,
                     removeItem : (Task)->Unit,
                     sortItems : MutableState<Sort?>,
                     filterItems : MutableState<Filter>,
                     onClick : (Task)->Unit) {


    val mappedData = remember { mutableStateListOf<Task>() }

    val haptic = LocalHapticFeedback.current

    val state = rememberReorderableLazyListState(
        onMove = { from, to ->
            mappedData.apply {
                add(to.index, removeAt(from.index)) }
            haptic.performHapticFeedback(HapticFeedbackType.LongPress) })


    LaunchedEffect(taskState, sortItems.value, filterItems.value) {
        mappedData.apply {
            this.clear()
            val filteredTasks = when (filterItems.value) {
                Filter.All -> taskState.data
                Filter.PENDING -> taskState.data.filter { !it.isCompleted }
                Filter.COMPLETED -> taskState.data.filter { it.isCompleted } }

            addAll(
                when (sortItems.value) {
                    Sort.PRIORITY -> filteredTasks.sortedByDescending { it.priority.ordinal }
                    Sort.ALPHABETICAL_ASCENDING -> filteredTasks.sortedBy { it.title }
                    Sort.ALPHABETICAL_DESCENDING -> filteredTasks.sortedByDescending { it.title }
                    Sort.DUE_NEWEST -> filteredTasks.sortedByDescending { it.dueDate }
                    Sort.DUE_OLDEST -> filteredTasks.sortedBy { it.dueDate }
                    else -> filteredTasks // No sorting if null
                }
            )
        } }


    LazyColumn(modifier = Modifier.fillMaxSize()
        .reorderable(state)
        .detectReorderAfterLongPress(state),
        state = state.listState
    ) {




        items(count = mappedData.size,
               key = {index : Int -> mappedData[index].id }){index ->
            ReorderableItem(state, key = mappedData[index].id) { isDragging ->

            TaskItemWithSwipe(mappedData =mappedData,
                onClick = onClick,
                index = index, removeAt = removeItem,
                isDragging = isDragging)
        }
    }
    }
}