package com.example.todolisttask.ext

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun PrimaryColorSelector(
    primaryColor: State<Color>,
    onColorPickerClick: () -> Unit) {

    Row(verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth()) {

        Text(text = "change Primary Color", style = MaterialTheme.typography.bodyLarge)

        Box(
            modifier = Modifier
                .size(50.dp)
                .clip(CircleShape)
                .background(primaryColor.value)
                .border(2.dp, Color.Gray, CircleShape)
                .clickable { onColorPickerClick() }
        )
    }
}