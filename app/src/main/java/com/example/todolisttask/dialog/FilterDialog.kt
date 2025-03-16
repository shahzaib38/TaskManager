package com.example.todolisttask.dialog

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.remember
import com.example.todolisttask.filter.Filter
import com.example.todolisttask.menu.MenuOverlay

@Composable
fun  FilterDialog(
    onOptionSelected:(Filter)->Unit,
    selectedItems : MutableState<Filter>,
    dismiss:()->Unit){



    val list =  remember { Filter.entries }



    MenuOverlay(true , onDismiss = dismiss ){
        FilterMenu(menuOptions = list,
            onOptionSelected = onOptionSelected,
            selectedItems = selectedItems
        )



    }


}