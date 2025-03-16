package com.example.todolisttask.dialog

import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import com.example.todolisttask.filter.Filter
import com.example.todolisttask.menu.TaskMenuOption
import com.example.todolisttask.model.DialogType
import com.example.todolisttask.sort.Sort
import com.example.todolisttask.viewmodel.TaskViewModel


@Composable
fun TaskDialogManager(taskViewModel: TaskViewModel ,
                      onOptionMenu:(TaskMenuOption)->Unit={}){

    val dialogType by  remember { derivedStateOf { taskViewModel.currentDialog.value } }
    var  date by  remember { mutableStateOf(0L) }

    val sortOptionMenu = remember<(Sort)->Unit > { {sort ->
        taskViewModel.sort(sort) } }

    val filterOptionMenu = remember<(Filter)->Unit > { {filter  ->
        taskViewModel.filter(filter) } }

    val dismiss = remember { { taskViewModel.setDialog(DialogType.None)} }

    BackHandler(dialogType!=DialogType.None) {
        taskViewModel.setDialog(DialogType.None)}

    when(dialogType){
        DialogType.TaskMenu->{
            TaskMenuDialog(click = { taskViewModel.setDialog(DialogType.None)}
            , onOptionSelected = onOptionMenu)


        }

        DialogType.DatePicker ->{

            DatePickerDialog(onCancel = {
                taskViewModel.setDialog(DialogType.None)

            },
                onDismiss = {

                }, onDateSelected = { newDate ->

                    date = newDate
                    taskViewModel.setDialog(DialogType.TimePicker)

                    Log.i("TaskDialogManager" , "Date ${date}")

                })

        }

        DialogType.TimePicker ->{
            Log.i("TaskDialogManager" , "Time Picker")

            TimePickerDialog(
                onCancel = {
                    taskViewModel.setDialog(DialogType.None)
                }
                ,onDismiss = {

            }, onTimeSelected = { time ->
                    taskViewModel.setDateTime( date, time)
                    taskViewModel.setDialog(DialogType.None)

            })
        }


        DialogType.SortMenu ->{

            SortDialog(click = { },
                onOptionSelected = sortOptionMenu,
                selectedItems = taskViewModel.sortItems,
                dismiss = dismiss)

        }

        DialogType.Filter ->{


            FilterDialog(
                onOptionSelected = filterOptionMenu,
                selectedItems = taskViewModel.filterItems,
                dismiss = dismiss)

        }

        DialogType.ColorPicker ->{
            ColorPickerDialog(initialColor = taskViewModel.primaryColor,
                onDismiss = dismiss,
                onColorSelected = taskViewModel::setPrimaryColor)

        }

        DialogType.None->{ }
    }
}