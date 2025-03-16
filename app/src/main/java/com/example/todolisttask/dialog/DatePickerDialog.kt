package com.example.todolisttask.dialog

import androidx.compose.material3.DatePicker
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun  DatePickerDialog(onDateSelected :(Long)->Unit,
                      onDismiss:()->Unit,
                      onCancel : ()->Unit ){

    val datePickerState = rememberDatePickerState()
    androidx.compose.material3.DatePickerDialog (
        onDismissRequest = {
         //   showDatePicker = false

                           },
        confirmButton = {
            TextButton(onClick = {
                datePickerState.selectedDateMillis?.let { selectedMillis ->
                    onDateSelected(selectedMillis) // Pass selected date
                }
                onDismiss()

            }) {
                Text("OK")
            }
        },
        dismissButton = {
            TextButton(onClick = {
                datePickerState.selectedDateMillis?.let { selectedMillis ->
                    onDateSelected(selectedMillis) // Pass selected date
                }
                onCancel()

            }) {
                Text("Cancel")
            }
        }
        ) {

        DatePicker(state = datePickerState)

    }
}


