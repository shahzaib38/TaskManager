package com.example.todolisttask.systemUi

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.graphics.Color
import com.example.todolisttask.LocalSystemUiController


@Composable
fun ManageStatusBar(isVisible: Boolean , color : Color) {

    val localSystemController = LocalSystemUiController.current

    LaunchedEffect(isVisible) {
        // Set Status Bar to Purple
        localSystemController.setStatusBarColor(
            color = color, // Royal Purple
            darkIcons = isVisible // Light icons for contrast
        )


    }
}

    @Composable
    fun ManageNavigationBar(isVisible: Boolean , color : Color) {
        val localSystemController = LocalSystemUiController.current
        LaunchedEffect(isVisible) {
            localSystemController.setStatusBarColor(
                color = color,
                darkIcons = isVisible
            )
        }
    }

