package com.example.todolisttask.ext

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.todolisttask.R
import com.example.todolisttask.model.Task

@Composable
fun  CompletionView(task: State<Task>,
                    onComplete:(Boolean)->Unit ){




    Column(horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier) {

        CompletionItem(
            taskState = task,
            onComplete = onComplete)

        Separator()
    } }


@Composable
fun CompletionItem(
              taskState: State<Task>,
              onComplete:(Boolean)->Unit){

    Row(verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .clickable { }
            .padding(4.dp)) {

        RadioItem(taskState = taskState,
            onComplete = onComplete)

        Text(text = "Completed",
            modifier = Modifier.padding(start = 8.dp),
            style = MaterialTheme.typography.bodyMedium) } }


@Composable
fun  RadioItem(taskState : State<Task>,
                         tint : Color = Color.Black,
                         checkIcon : Int = R.drawable.check_radio_icon,
                         unCheckedIcon : Int = R.drawable.unchecked_radio_icon,
                         onComplete: (Boolean) -> Unit){

    val isCompleted by  remember { derivedStateOf { taskState.value.isCompleted} }

    Log.i("CreatedView" , "IsCompleted  ${isCompleted}" )

    Icon(painter = painterResource(
        if(taskState.value.isCompleted)
            checkIcon
        else unCheckedIcon),
        contentDescription = "Radio",
        tint = tint,

        modifier = Modifier.clickable { onComplete(!isCompleted)

        })


}






//

//@Composable
//fun CreationLabel(icon : Int,
//              text : String,
//              color :Color,
//              timeText : String,
//
//              modifier: Modifier =  Modifier){
//
//    Row(horizontalArrangement = Arrangement.spacedBy(8.dp),
//        verticalAlignment = Alignment.CenterVertically,
//
//        modifier = modifier) {
////
//        Icon(painter = painterResource(icon),
//            contentDescription = text,
//            tint = color)
//
//        Column (horizontalAlignment = Alignment.Start,
//            verticalArrangement = Arrangement.Center){
//
//            Text(text)
//
//            Text(timeText,
//                color = Color.Gray,
//                modifier = Modifier,
//                fontFamily = FontFamily(listOf(Font(R.font.roboto_light)))
//            )
//        }
//
//    }
//
//}