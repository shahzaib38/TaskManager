package com.example.todolisttask.menu

import androidx.compose.foundation.clickable


import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp


@Composable
fun <T:MenuOption> MenuItem(text : T , onClick: ()->Unit  ){

    Text(text.optionName, modifier = Modifier.clickable {
        onClick() }
        .padding(
            start = 12.dp, end = 42.dp,
            top = 10.dp, bottom = 6.dp)
        .wrapContentWidth(Alignment.Start),
        fontSize = TextUnit(16f, TextUnitType.Sp),
        color = Color.Black)

}