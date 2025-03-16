package com.example.todolisttask.ui

import androidx.compose.material3.ColorScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun dynamicColorScheme(isDarkTheme: Boolean, primary: Color): ColorScheme {
    return if (isDarkTheme) {
        darkColorScheme(primary = primary)
    } else {
        lightColorScheme(primary = primary)
    }
}