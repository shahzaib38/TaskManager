package com.example.todolisttask.viewmodel

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.todolisttask.common.Common
import com.example.todolisttask.ext.Priority
import com.example.todolisttask.filter.Filter
import com.example.todolisttask.model.DialogType
import com.example.todolisttask.model.Task
import com.example.todolisttask.model.TaskState
import com.example.todolisttask.repo.TaskRepository
import com.example.todolisttask.sort.Sort
import com.example.todolisttask.ui.theme.Purple40
import com.example.todolisttask.ui.theme.Purple80
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TaskViewModel  @Inject constructor(
    private val repository : TaskRepository

) : ViewModel() {



    val isDarkTheme =  mutableStateOf(false )
    var currentDialog = mutableStateOf<DialogType>(DialogType.None)

    private val _snackbarFlow = MutableSharedFlow<String>()
    val snackbarFlow = _snackbarFlow.asSharedFlow()

    private val _Task = mutableStateOf(Task())
    val task: State<Task> get() = _Task

    val sortItems =  mutableStateOf<Sort?>(null )
    val filterItems =  mutableStateOf<Filter>(Filter.All )


    val primaryColor = mutableStateOf(Purple40)


   fun  setTheme(isDark : Boolean){
       isDarkTheme.value = isDark }

    fun updateCompleted(isCompleted : Boolean ){
//        _Task.update {
//            it.copy(isCompleted = isCompleted)
//        }

    }






    fun setDialog(none: DialogType) {
        currentDialog.value = none }



    val tasks by lazy {
        repository.tasks.map { mediaList ->
                TaskState(data = mediaList, isLoading = false) }
            .distinctUntilChanged()
            .flowOn(Dispatchers.IO)
            .stateIn( scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(),
                initialValue = TaskState(data = emptyList(), isLoading = true))
    }

    fun addTask(): Boolean{

        if(!task.value.hasValidTitle()) {
            viewModelScope.launch {
                _snackbarFlow.emit("Title Required") }

            return false }

        viewModelScope.launch(Dispatchers.IO) {
            repository.addTask(task.value) }

        return true
    }

    fun updateTitle(newTitle: String) {
        if (_Task.value.title != newTitle) { // Only update if different
            _Task.value = _Task.value.copy(title = newTitle)
        }
    }
    fun updateDescription( description : String) {
        if (_Task.value.description != description) {
            _Task.value = _Task.value.copy(description = description) } }

    fun setPriority(priority: Priority) {
        _Task.value = _Task.value.copy(priority = priority) }

    fun setDateTime(date: Long,
                    time: Long) {

     val timeMillis =   Common.mergeDateTime(dateMillis = date, timeMillis = time)
        _Task.value = _Task.value.copy(dueDate = timeMillis)

    }

    fun removeTask(task: Task) {
        viewModelScope.launch(Dispatchers.IO) {
           repository.remove(task) } }

    fun sort(sort: Sort) {
        sortItems.value  = sort
        currentDialog.value = DialogType.None }

    fun filter(filter: Filter) {
        filterItems.value = filter
        currentDialog.value = DialogType.None }


    fun setPrimaryColor(color: Color) {
        primaryColor.value = color }

    fun onComplete(isCompleted : Boolean) {
        _Task.value = _Task.value.copy(isCompleted = isCompleted)

        Log.i("TaskViewModel" , "Clicked ${isCompleted}")

    }

    fun updateId(id: Long) {
        _Task.value = _Task.value.copy(id = id )


    }


}