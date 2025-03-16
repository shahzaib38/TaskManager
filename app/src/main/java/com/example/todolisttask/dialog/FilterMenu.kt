package com.example.todolisttask.dialog

import com.example.todolisttask.filter.Filter

import androidx.compose.foundation.layout.padding
import com.example.todolisttask.menu.MenuOption
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import com.example.todolisttask.sort.Sort


@Composable
fun  FilterMenu(menuOptions: List<Filter>,
              onOptionSelected: ((Filter) -> Unit),
              cardsColor  : CardColors = CardColors(
                  containerColor = Color.White,
                  contentColor = Color.Black.copy(alpha = .4f),
                  disabledContainerColor = Color.Transparent,
                  disabledContentColor = Color.Transparent),
              cardShape : Shape = RoundedCornerShape(8.dp),
              selectedItems : MutableState<Filter>){

    Card(shape = cardShape,
        colors = cardsColor) {

        FilterList(menuOptions = menuOptions,
            onOptionSelected= onOptionSelected,
            selectedItem = selectedItems
        )
    }


}