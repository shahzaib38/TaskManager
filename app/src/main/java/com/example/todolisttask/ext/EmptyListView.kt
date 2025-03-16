package com.example.todolisttask.ext

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.todolisttask.R


@Composable
fun  EmptyListView(){

    Box(modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center){
        Column(horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center) {
            EmptyIconView()
            EmptyMessageView() } }
}


@Composable
fun EmptyIconView(icon : Int  = R.drawable.empty_icon,
                  modifier: Modifier = Modifier
                      .fillMaxWidth(0.3f)
                      .aspectRatio(1f)){
    Box(modifier = modifier) {
        Icon(
            painter = painterResource(icon),
            contentDescription = "Empty Icon",
            tint = Color.Unspecified) } }

@Composable
fun EmptyMessageView(message : String ="No tasks yet. Plan your day now!",
                     style: TextStyle = MaterialTheme.typography.bodyMedium,
                     modifier: Modifier = Modifier.padding(top = 8.dp)) {
    Text(text = message,
        style = style,
        textAlign = TextAlign.Center,
        modifier = modifier)
}

