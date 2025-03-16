package com.example.todolisttask.dialog

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.remember
import com.example.todolisttask.menu.MenuOverlay
import com.example.todolisttask.sort.Sort


@Composable
fun  SortDialog( click:()->Unit,
                 onOptionSelected:(Sort)->Unit,
                 selectedItems : MutableState<Sort?>,
                 dismiss:()->Unit
){



    val list =  remember { Sort.entries }

    MenuOverlay(true , onDismiss = dismiss ){
        SortMenu(menuOptions = list,
            onOptionSelected = onOptionSelected,
            selectedItems = selectedItems
        )



    }

}