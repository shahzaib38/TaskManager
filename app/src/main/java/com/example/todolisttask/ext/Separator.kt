package com.example.todolisttask.ext

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@Composable
fun Separator(modifier: Modifier = Modifier
    .padding(vertical = 4.dp)
    .fillMaxWidth()
    .height(1.dp)
    .background(Color.Gray.copy(alpha = .5f))){
    Box(modifier = modifier)

}