package com.example.todolisttask.ext

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import com.example.todolisttask.R
import com.example.todolisttask.common.Common
import com.example.todolisttask.model.Task


@Composable
fun DateView(modifier: Modifier = Modifier,
             onTimeChange : ()->Unit,
             task : State<Task>){

    var  textChange = remember { mutableStateOf("") }
    val dueDate by  remember { derivedStateOf { task.value.dueDate } }



    val color by   rememberUpdatedState(
        if(textChange.value.isNotEmpty())
            Color(android.graphics.Color.parseColor("#FD3DB5"))
        else Color.Black)

    DateLabel(icon = R.drawable.clock_icon,
        text = "Due Date",
        color = color,
        timeText = Common.formatMillis(dueDate),
        modifier = Modifier.clickable { onTimeChange() },
        task = task
        )

    Separator()
}




@Composable
fun DateIcon(icon:Int,text: String ,task: State<Task>){


    val dateColor by remember { derivedStateOf { if(task.value.dueDate > 0 ) Color.Blue else Color.Black} }

    Icon(painter = painterResource(icon),
        contentDescription = text,
        tint = dateColor)
}


@Composable
fun DateLabel(icon : Int,
              text : String,
              color :Color,
              timeText : String,

              modifier: Modifier =  Modifier,
              task: State<Task> ){

    Row(horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically,

        modifier = modifier.fillMaxWidth()) {

        DateIcon(icon, text, task)

//        Icon(painter = painterResource(icon),
//            contentDescription = text,
//            tint = color)

        Column (horizontalAlignment = Alignment.Start,
               verticalArrangement = Arrangement.Center){

            Text(text)

            Text(timeText,
                color = Color.Gray,
                modifier = Modifier,
                fontFamily = FontFamily(listOf(Font(R.font.roboto_light)))
            )
        }

    }

}
