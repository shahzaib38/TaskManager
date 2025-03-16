package com.example.todolisttask.ext

import androidx.compose.ui.graphics.Color

enum class Priority {
    NONE, LOW,MEDIUM,HIGH }


enum class PriorityColors(color :Color){

    BLACK(color = Color.Black),
    YELLOW(color = Color.Yellow),
    ORANGE(color = Color.Blue),
    RED(color = Color.Red)

}