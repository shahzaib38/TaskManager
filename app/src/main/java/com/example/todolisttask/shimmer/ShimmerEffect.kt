package com.example.todolisttask.shimmer

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@Composable
fun  ShimmerEffect(){



    LazyColumn(modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(horizontal = 8.dp,
            vertical = 24.dp )
    ) {

        items(11){
            ShimmerItem() }

    }

}

@Composable
fun ShimmerItem(){

    val shimmerColors = listOf(
        Color.LightGray.copy(alpha = 0.6f),
        Color.Gray.copy(alpha = 0.3f),
        Color.LightGray.copy(alpha = 0.6f))


    val transition = rememberInfiniteTransition(label = "Shimmer")
    val alpha by transition.animateFloat(
        initialValue = 0f,
        targetValue = 1000f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse),
        label = "Shimmer Alpha")

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .height(80.dp)
            .background(
                brush = Brush.linearGradient(shimmerColors),
                shape = RoundedCornerShape(8.dp))
            .alpha(alpha)
    )


}