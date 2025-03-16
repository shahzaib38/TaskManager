package com.example.todolisttask.ext

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.todolisttask.R
import com.example.todolisttask.model.Task


@Composable
fun  DescriptionView(modifier: Modifier = Modifier,
                     task:State<Task>,
                     onValueChanged:(String)->Unit){



      Column(modifier = modifier) {

        DescriptionTitle(task)
        Spacer(modifier = Modifier.height(4.dp))

          DescriptionTextField(task= task, onValueChanged = onValueChanged)
          Separator()
    }
}

@Composable
fun DescriptionTitle(task  : State<Task>){

    LabelIcon(color =   Color(android.graphics.Color.parseColor("#FD3DB5")), task = task)

}









@Composable
fun LabelIcon(color: Color,
                   text : String = "Description (Optional)",
                   icon :Int = R.drawable.description_icon,
              task: State<Task>
              ) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start) {

        LabeledIcon(color = color ,icon=icon, text = text, task = task)
        Spacer(modifier = Modifier.width(8.dp))
        Text(text)
    }
}

@Composable
fun LabeledIcon(color:Color ,icon: Int,text: String,
                task: State<Task>
                ){



    val newColor by  remember { derivedStateOf {    if(task.value.description.isNotEmpty())
        color
    else Color.Black  } }


    Icon(painter = painterResource(icon),
        contentDescription = text,
        tint = newColor)


}

