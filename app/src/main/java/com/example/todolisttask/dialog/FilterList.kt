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
import com.example.todolisttask.filter.Filter
import com.example.todolisttask.sort.Sort


@Composable
fun  FilterList(menuOptions: List<Filter>,
              onOptionSelected: ((Filter) -> Unit),
              selectedItem : MutableState<Filter>){

    LazyColumn(modifier = Modifier
        .padding(horizontal = 4.dp,
            vertical = 16.dp),

        verticalArrangement = Arrangement.spacedBy(6.dp)){

        items(menuOptions.size) { index ->
            FilterItem(menuOptions[index], onOptionSelected,
                selectedItem)


        }

    } }
