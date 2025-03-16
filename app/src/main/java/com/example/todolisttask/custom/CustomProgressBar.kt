package com.example.todolisttask.custom

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import com.example.todolisttask.R
import com.example.todolisttask.model.Task

@Composable
fun  CustomProgressBar(animatedSweepAngle: Float){

    val gradientBrush = Brush.sweepGradient(
        colors = listOf(Color.Magenta, Color.Blue, Color.Cyan))

    Box(modifier = Modifier.fillMaxSize()
            .padding(all = 12.dp)
            .drawBehind {

                val strokeWidthPx = this.size.width * 0.1f
                val outerColor = Color.Gray.copy(alpha = 0.3f)

                drawArc(
                    color = outerColor,
                    startAngle = -90f,
                    sweepAngle = 360f,
                    useCenter = true,
                //    style = Stroke(width = strokeWidthPx, cap = StrokeCap.Round)

                )


                drawArc(
                    brush = gradientBrush,
                    startAngle = -90f,
                    sweepAngle = animatedSweepAngle,
                    useCenter = true ,
                //    style = Stroke(width = strokeWidthPx, cap = StrokeCap.Round)

                )


                        },
        contentAlignment = Alignment.Center){
//        StatusView(isCompleted,animatedSweepAngle)
    }
}


@Composable
fun StatusView(
    isCompleted : Boolean,
    animatedSweepAngle : Float){

    val percentage = (animatedSweepAngle / 360f * 100).toInt()

    Column(horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
        ) {
        Text(
            if (!isCompleted) "Pending" else "Completed",
            fontFamily = FontFamily(listOf(Font(R.font.roboto_light))))

        Text(
            "${percentage}%",
            fontFamily = FontFamily(listOf(Font(R.font.roboto_light))))

    }

}