package com.example.todolisttask.ext

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SwipeToDismiss
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.todolisttask.R
import com.example.todolisttask.common.Common
import com.example.todolisttask.model.Task


@Composable
fun  TaskItem(task: Task ,
              titleStyle: TextStyle = MaterialTheme.typography.titleMedium,
              descStyle: TextStyle = MaterialTheme.typography.bodyMedium,
              onItemClick : (Task)->Unit) {

    Card(modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp).clickable { onItemClick(task) },
        elevation = CardDefaults.cardElevation(4.dp),
        shape = RoundedCornerShape(12.dp)
        ) {

        TaskContent(
            task = task,
            titleStyle = titleStyle,
            descStyle = descStyle) }

}


@Composable
fun TaskContent(
    task: Task,
    titleStyle: TextStyle,
    descStyle: TextStyle){
    Column(modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp)) {

        Text(text = task.title,
            style = titleStyle,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis)

        if(task.description.isNotEmpty())
          Text(text = task.description ?: "No description",
            style = descStyle,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis)

        if(task.dueDate>0)
        FormatDate(task)

        Text(
            text = if (task.isCompleted) "Completed" else "Pending",
            style = descStyle,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )


    }

}


@Composable
fun FormatDate(task : Task){

    Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {

        Icon(painter = painterResource(R.drawable.clock_icon),
            contentDescription = "Icon",
            tint = Common.colors.get(task.priority.ordinal))
        Text(Common.formatMillis(task.dueDate),
            fontFamily = FontFamily(listOf(Font(R.font.roboto_light))))
    }





}