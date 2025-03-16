package com.example.todolisttask.dialog

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.todolisttask.sort.Sort


@Composable
fun  SortList(menuOptions: List<Sort>,
              onOptionSelected: ((Sort) -> Unit),
              selectedItem : MutableState<Sort?>
              ){

    LazyColumn(modifier = Modifier
        .padding(horizontal = 4.dp,
        vertical = 16.dp),

        verticalArrangement = Arrangement.spacedBy(6.dp)
        ){

        items(menuOptions.size) { index ->
            SortItem(menuOptions[index], onOptionSelected,
                selectedItem) } } }
