package com.example.todolisttask.ext

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.todolisttask.R
import com.example.todolisttask.model.Task


@Composable
fun  DescriptionTextField(task: State<Task>,
                          onValueChanged:(String)->Unit){



    val description by  remember { derivedStateOf { task.value.description } }

    val textStyle = TextStyle(
        fontSize = 16.sp,
        fontFamily = FontFamily(Font(R.font.roboto_light)),
        color = Color.Black // Adjust as needed
                 )

    BasicTextField(textStyle = textStyle,
        value = description,
        onValueChange = onValueChanged,
        decorationBox = { decorationBox->

            Box(modifier = Modifier.padding(4.dp)) {
                if (description.isEmpty())
                    Text(text = "Tap to add Description...",
                        color = Color.Gray,
                        fontFamily = FontFamily(listOf(Font(R.font.roboto_light))),
                        fontSize =   16.sp)


                decorationBox.invoke()
            }
        })

}