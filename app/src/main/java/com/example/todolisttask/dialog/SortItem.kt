package com.example.todolisttask.dialog

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import com.example.todolisttask.R
import com.example.todolisttask.ext.Priority
import com.example.todolisttask.ext.PriorityRadioButton
import com.example.todolisttask.menu.MenuOption
import com.example.todolisttask.model.Task
import com.example.todolisttask.sort.Sort


@Composable
fun  SortItem(sort  : Sort,
              onSort: (Sort)->Unit,
              selectedItems : MutableState<Sort?>){


    Row(modifier = Modifier.
        clickable { onSort(sort) }.
    padding(
           horizontal = 24.dp, vertical = 8.dp ),
        horizontalArrangement = Arrangement.spacedBy(6.dp),
        verticalAlignment = Alignment.CenterVertically) {

        SortSelectIcon(selectedPriority = selectedItems, sort = sort)

        Text(sort.optionName, modifier = Modifier
            .wrapContentWidth(Alignment.Start),
            fontSize = TextUnit(16f, TextUnitType.Sp),
            color = Color.Black)
    }

}


//


@Composable
fun  SortSelectIcon(selectedPriority : State<Sort?>,
                         sort: Sort
                         , tint : Color = Color.Black,
                         checkIcon : Int = R.drawable.check_radio_icon,
                         unCheckedIcon : Int = R.drawable.unchecked_radio_icon){



    val isSelected by remember { derivedStateOf { selectedPriority.value == sort } }


    Log.i("SortItem" , "Sort Item ${sort}    SelectedSort ${selectedPriority.value }")



    Icon(painter = painterResource(
        if(isSelected)
            checkIcon
        else unCheckedIcon),
        contentDescription = "Radio",
        tint = tint)


}