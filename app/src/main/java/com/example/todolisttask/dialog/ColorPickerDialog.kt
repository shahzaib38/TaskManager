package com.example.todolisttask.dialog

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.todolisttask.ui.theme.Purple80


@Composable
fun ColorPickerDialog(
    initialColor: State<Color>,
    onDismiss: () -> Unit,
    onColorSelected: (Color) -> Unit) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(text = "Pick a Color") },
        text = {

            val colors = listOf(
                Color(0xFFE91E63),
                Color(0xFF9C27B0),
                Color(0xFF673AB7),
                Purple80)


            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.fillMaxWidth()
            ) {
                colors.forEach { color ->
                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .clip(CircleShape)
                            .background(color)
                            .border(
                                width = if (color == initialColor.value) 3.dp else 1.dp,
                                color = Color.Black,
                                shape = CircleShape)
                            .clickable { onColorSelected(color) }
                    )
                }
            }
        },
        confirmButton = {
            Button(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    )
}