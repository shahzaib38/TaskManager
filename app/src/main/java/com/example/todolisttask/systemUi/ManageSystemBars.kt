package com.example.todolisttask.systemUi



import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.graphics.Color
import com.example.todolisttask.LocalSystemUiController


@Composable
fun ManageSystemBars(isVisible: Boolean , barColor : Color, darkIcons : Boolean = true ) {
    val localSystemController = LocalSystemUiController.current
    LaunchedEffect(isVisible) {
        localSystemController.setSystemBarsColor(
            color = barColor,
            darkIcons = darkIcons
        )
    }
}
