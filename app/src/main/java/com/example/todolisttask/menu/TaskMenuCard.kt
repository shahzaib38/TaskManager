package com.example.todolisttask.menu

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp


@Composable
fun <T: MenuOption> MenuCard(menuOptions: List<T>,
                             onOptionSelected: ((Int) -> Unit),
                             cardsColor  : CardColors = CardColors(
                                 containerColor = Color.White,
                                 contentColor = Color.Black.copy(alpha = .4f),
                                 disabledContainerColor = Color.Transparent,
                                 disabledContentColor = Color.Transparent),
                             cardShape : Shape = RoundedCornerShape(8.dp)
){

    Card(shape = cardShape,
        colors = cardsColor) {
        MenuList(menuOptions = menuOptions,onOptionSelected= onOptionSelected) } }