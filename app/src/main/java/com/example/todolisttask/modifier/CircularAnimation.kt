package com.example.todolisttask.modifier

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.clipPath
import com.example.todolisttask.screens.calculateDiagonalDistance


fun Modifier.circularReveal(
    progress: Animatable<Float, AnimationVector1D>,
    points: Offset) : Modifier = Modifier.drawWithCache {


        val path = Path()
        val radius = calculateDiagonalDistance(points.x,points.y,size.width,size.height)
       val center =  points.mapTo(size)

        path.addOval(Rect(center, radius * progress.value))

        onDrawWithContent {
            clipPath(path) {
                this@onDrawWithContent.drawContent() }
        } }



private fun Offset.mapTo(size: Size): Offset {
    return Offset(x * size.width, y * size.height)
}
