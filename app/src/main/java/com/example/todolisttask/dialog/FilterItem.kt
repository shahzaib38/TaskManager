package com.example.todolisttask.dialog

import com.example.todolisttask.filter.Filter


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


@Composable
fun  FilterItem(filter  : Filter,
               onFilter: (Filter)->Unit,
                selectedItems : MutableState<Filter>){


    Row(modifier = Modifier.
    clickable { onFilter(filter) }.
    padding(
        horizontal = 24.dp, vertical = 8.dp ),
        horizontalArrangement = Arrangement.spacedBy(6.dp),
        verticalAlignment = Alignment.CenterVertically) {

        FilterSelectIcon(selectedPriority = selectedItems, sort = filter)

        Text(filter.optionName, modifier = Modifier
            .wrapContentWidth(Alignment.Start),
            fontSize = TextUnit(16f, TextUnitType.Sp),
            color = Color.Black)
    }

}


//


@Composable
fun  FilterSelectIcon(selectedPriority : State<Filter?>,
                    sort: Filter
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