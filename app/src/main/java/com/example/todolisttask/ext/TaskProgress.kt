package com.example.todolisttask.ext

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.todolisttask.custom.CustomProgressBar
import com.example.todolisttask.model.Task

@Composable
fun TaskProgress(task: State<Task>){


    val isCompleted by  remember { derivedStateOf { task.value.isCompleted } }

    val animatedSweepAngle by animateFloatAsState(
        targetValue = if (isCompleted) 360f else 180f, // Animate to full or half circle
        animationSpec = tween(durationMillis = 1000, easing = FastOutSlowInEasing), // Smooth animation
        label = "Sweep Angle Animation")

    val percentage = (animatedSweepAngle / 360f * 100).toInt()

    Row(modifier = Modifier.fillMaxWidth()
        .wrapContentHeight(),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically){

        Box(modifier = Modifier.weight(0.3f).aspectRatio(1f)) {
            CustomProgressBar(animatedSweepAngle) }

        Column(modifier = Modifier.weight(.7f).padding(horizontal = 12.dp)
            .wrapContentHeight().background(Color.Transparent),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.Start) {

            Text("Task Progress: ${percentage}%", textAlign = TextAlign.Start,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
                )
            Text("Status: ${if (isCompleted) "Completed" else "Pending"}", textAlign = TextAlign.Start,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis)

        } }

    Separator()

}