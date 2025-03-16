package com.example.todolisttask.ext

import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import com.example.todolisttask.R
import com.example.todolisttask.model.Task


@Composable
fun  PriorityRadioButton(selectedPriority : State<Task>,
                         priority: Priority
                         ,tint : Color = Color.Black,
                         checkIcon : Int = R.drawable.check_radio_icon,
                         unCheckedIcon : Int = R.drawable.unchecked_radio_icon){


    val isSelected by remember { derivedStateOf { selectedPriority.value.priority == priority } }




    Icon(painter = painterResource(
        if(isSelected)
            checkIcon
        else unCheckedIcon),
        contentDescription = "Radio",
        tint = tint)


}