package com.example.todolisttask.ext

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay


@Composable
fun  BountyFAB(
    navigateToTaskCreation:()->Unit,
    color: Color  = MaterialTheme.colorScheme.primary,
    icon : ImageVector =  Icons.Default.Add,
    tint : Color = Color.White,
    onClick:()->Unit = {}){

    var  pressed by  remember { mutableStateOf(false ) }
    val scale by  animateFloatAsState(if(pressed) 0.75f else 1f ,
        animationSpec = tween(durationMillis = 500))

    LaunchedEffect(pressed) {
        if (pressed ) {
            delay(500)
            navigateToTaskCreation()
            pressed = false }
    }

    FloatingActionButton(
        onClick = {
            if (!pressed) {
                pressed = true } },
        modifier = Modifier
                .scale(scale)
            .padding(16.dp),
        containerColor = color) {
        Icon(imageVector = icon,
            contentDescription = "Add Task",
            tint = tint)
    }

}