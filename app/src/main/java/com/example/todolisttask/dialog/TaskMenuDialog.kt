package com.example.todolisttask.dialog

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import com.example.todolisttask.LocalSystemUiController
import com.example.todolisttask.menu.MenuCard
import com.example.todolisttask.menu.MenuOverlay
import com.example.todolisttask.menu.TaskMenuOption


@Composable
fun TaskMenuDialog(
     click:()->Unit,
     onOptionSelected : (TaskMenuOption )->Unit ){

    val localSystemController = LocalSystemUiController.current

    LaunchedEffect(localSystemController) {
        localSystemController.setSystemBarsColor(
                Color.Black.copy(.15f),
            darkIcons = true ) }


    val list =  remember { TaskMenuOption.entries }

    MenuOverlay(true , onDismiss = { click() }) {
        MenuCard(menuOptions = list,
            onOptionSelected = {index->
                onOptionSelected(list[index])
            })

    }

}