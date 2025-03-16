package com.example.todolisttask.menu


import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable


@Composable
fun <T:MenuOption>  MenuList(menuOptions: List<T>,onOptionSelected: ((Int) -> Unit)){
    LazyColumn {
        items(menuOptions.size) { index ->
            MenuItem(menuOptions[index], onClick = { onOptionSelected(index)})

        }
    }
}
