package com.example.todolisttask.ext

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.todolisttask.R
import com.example.todolisttask.common.Common
import com.example.todolisttask.model.Task


@Composable
fun  PrioritySelection(modifier: Modifier = Modifier,
                       task: State<Task>,
                       onPrioritySelected: (Priority) -> Unit){


    Column(modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(6.dp)) {

        PriorityLabel(
            color = Color.Black,
            icon = R.drawable.priority_icon,
            text = "Priority",
            task = task)

        PriorityList(task = task,
            onPrioritySelected = onPrioritySelected)
        Separator()



    } }


@Composable
fun PriorityLabel(color: Color = Color.Transparent,
                  text : String = "Description (Optional)",
                  icon :Int = R.drawable.description_icon,
                  task: State<Task>){


    val priorityColor  by  remember { derivedStateOf { task.value.priority } }

    Log.i("PriorityView" , "Color ${priorityColor}")

    Row(verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start) {

        PriorityIcon(icon =icon,
            text = text,
            task = task)

        Spacer(modifier = Modifier.width(8.dp))

        Text(text)

    }
}

@Composable
fun PriorityIcon( task: State<Task>,
                  icon :Int ,
                  text: String){


    val priorityColor  by  remember{ derivedStateOf{ task.value.priority } }

    Icon(painter = painterResource(icon),
        contentDescription = text,
        tint = Common.colors[priorityColor.ordinal])

}



//


@Composable
fun PriorityList(task : State<Task>,
                 onPrioritySelected: (Priority) -> Unit){

    val prioritiesList =  remember {  Priority.entries.toList()}

    LazyColumn {
        items(count = prioritiesList.size){index ->
            RadioItem(priority = prioritiesList[index],
                task,
                onPrioritySelected = onPrioritySelected) } } }


@Composable
fun RadioItem(priority: Priority,
              task: State<Task>,
              onPrioritySelected:(Priority)->Unit){

    Row(verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onPrioritySelected(priority)}
            .padding(4.dp)) {

        PriorityRadioButton(selectedPriority =task,
            priority = priority
            )

        Text(text = priority.name.lowercase().replaceFirstChar { it.uppercase() },
            modifier = Modifier.padding(start = 8.dp),
            style = MaterialTheme.typography.bodyMedium) }


}