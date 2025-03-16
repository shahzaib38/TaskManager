package com.example.todolisttask.ext

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.Placeholder
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.todolisttask.R
import com.example.todolisttask.model.Task

@Composable
fun  TaskTextField(task:State<Task>,
                   onValueChange :(String)->Unit,
                   singleLine : Boolean = false,
                   placeholder: String = "Add Task Name...",
                   textColor : Color = Color(android.graphics.Color.parseColor("#C2A2E0"))
                   ){

    val title by remember { derivedStateOf { task.value.title } } // Prevents extra recompositions




    val textStyle = TextStyle(
        fontSize = 16.sp,
        fontFamily = FontFamily(Font(R.font.roboto_light)),
        color = Color.White)

    BasicTextField(cursorBrush  = SolidColor(Color.White),
    textStyle = textStyle,
    value = title,
    onValueChange = onValueChange,
    decorationBox = { decorationBox->

        Box(modifier = Modifier.padding(4.dp)) {

            if (title.isEmpty())
                Text(text = placeholder,
                    color = textColor,
                    fontFamily = FontFamily(listOf(Font(R.font.roboto_light)))
                    ,fontSize =   16.sp)

            decorationBox.invoke()
        }
    },
    keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next), // Set "Next" action
        
        singleLine = singleLine
    )

}


//



//        BasicTextField(cursorBrush  = SolidColor(Color.White),
//            textStyle = textStyle,
//            value = task.value.title,
//            onValueChange = onValueChange,
//            decorationBox = { decorationBox->
//
//                Box(modifier = Modifier.padding(4.dp)) {
//
//                    if (task.value.title.isEmpty())
//                        Text(text = "Add Task Name...",
//                            color = Color(android.graphics.Color.parseColor("#C2A2E0")),
//                            fontFamily = FontFamily(listOf(Font(R.font.roboto_light)))
//                            ,fontSize =   16.sp)
//
//                    decorationBox.invoke()
//                }
//            },
//            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next), // Set "Next" action
//            )



