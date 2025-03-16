package com.example.todolisttask.ext

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.EaseInBack
import androidx.compose.animation.core.EaseInQuad
import androidx.compose.animation.core.EaseOutBack
import androidx.compose.animation.core.EaseOutQuad
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SwipeToDismissBoxState
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.todolisttask.R
import kotlinx.coroutines.delay


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun  DismissBackground (swipeToDismissBoxState: SwipeToDismissBoxState,
                        isCompleted : State<Boolean>,
                        remove:()->Unit
                        ){




    val isDone by  remember { derivedStateOf { isCompleted.value } }
    val isSwipedLeftToRight by  remember {
        derivedStateOf { swipeToDismissBoxState.dismissDirection == SwipeToDismissBoxValue.StartToEnd }
    }

    val isSwipedRightToLeft by  remember {
        derivedStateOf { swipeToDismissBoxState.dismissDirection == SwipeToDismissBoxValue.EndToStart }
    }

    val backgroundColor = when (swipeToDismissBoxState.dismissDirection) {
        SwipeToDismissBoxValue.StartToEnd -> Brush.horizontalGradient(
            listOf(Color(0xFFFF6B6B), Color(0xFFC30000)) // Red Gradient for Delete
        )
        SwipeToDismissBoxValue.EndToStart -> Brush.horizontalGradient(
            listOf(Color(0xFF2E7D32), Color(0xFF2E7D32)) // Green Gradient for Complete
        )
        else -> Brush.horizontalGradient(listOf(Color.Gray, Color.DarkGray)) }


    LaunchedEffect(isDone) {

        if(isDone){
            delay(1000)
            remove()
        }
    }



    Log.i("DismissBackground" , "Left to right ${isSwipedLeftToRight}")

    Box(modifier = Modifier
        .padding(8.dp)
        .clip( RoundedCornerShape(12.dp))
        .fillMaxSize()
        .background(backgroundColor),
        contentAlignment = Alignment.CenterStart){

        AnimatedVisibility(visible = isSwipedLeftToRight,
            modifier = Modifier
                .align(Alignment.CenterStart)
                .padding(horizontal = 32.dp),
            enter = slideInHorizontally(
                initialOffsetX = { -it }, // Slide in from left
                animationSpec = tween(durationMillis = 500)
            ) + fadeIn(animationSpec = tween(500)),
            exit = shrinkHorizontally(
                shrinkTowards = Alignment.Start, // Shrink towards left
                animationSpec = tween(500)
            ) + fadeOut(animationSpec = tween(500))
            ) {
            Icon(painter = painterResource(R.drawable.delete_icon),
                tint = Color.White,
                contentDescription = "Delete")
        }



        AnimatedVisibility(visible = isSwipedRightToLeft,

            modifier = Modifier
                .align(Alignment.CenterEnd)
                .padding(horizontal = 32.dp),

            enter = slideInHorizontally(
                initialOffsetX = { it }, // Slide in from right
                animationSpec = tween(durationMillis = 500)
            ) + fadeIn(animationSpec = tween(500)),
            exit = shrinkHorizontally(
                shrinkTowards = Alignment.End, // Shrink towards right
                animationSpec = tween(500)
            ) + fadeOut(animationSpec = tween(500))

            ) {

        Column(modifier = Modifier,
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(4.dp)){
            Icon(
                painter = painterResource(R.drawable.check_icon),
                tint = Color.White,
                contentDescription = "Done") } }


        AnimatedVisibility(
            visible = isDone,
            enter = scaleIn(
                initialScale = 0.5f,
                animationSpec = tween(durationMillis = 500, easing = EaseOutBack)
            ) + fadeIn(animationSpec = tween(500)) + slideInVertically(
                initialOffsetY = { it }, // Start from bottom
                animationSpec = tween(500, easing = EaseOutQuad) // Smooth slide up
            ),
            exit = scaleOut(
                targetScale = 0.5f,
                animationSpec = tween(500, easing = EaseInBack)
            ) + fadeOut(animationSpec = tween(500)) + slideOutVertically(
                targetOffsetY = { -it }, // Exit towards top
                animationSpec = tween(500, easing = EaseInQuad))){


            Box(modifier = Modifier.fillMaxSize().background(Color.Magenta),
                contentAlignment = Alignment.Center){
                Text("Task Completed",
                    color = Color.White)
            } }


    }

}