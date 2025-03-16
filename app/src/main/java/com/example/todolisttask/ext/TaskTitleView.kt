package com.example.todolisttask.ext

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.todolisttask.R
import com.example.todolisttask.model.Task


@Composable
fun TaskTop(color:Color,
            task:State<Task>,
            onValueChange: (String) -> Unit){




    Column(modifier = Modifier
        .fillMaxWidth()
        .background(color,
            shape = RectangleShape )) {



        TaskTitleName(task ,
            onValueChange = onValueChange)
    }


}


@Composable
fun TaskTitleName(task : State<Task>, onValueChange :(String )->Unit  ){


    Column(modifier = Modifier
         .padding(horizontal = 32.dp, vertical = 16.dp )
         .fillMaxWidth()) {

        Text(text = "Task Name",
            modifier = Modifier.
            fillMaxWidth(), color = Color.White)

        Spacer(modifier = Modifier.height(2.dp))
        TaskTextField(task = task, onValueChange = onValueChange,
            singleLine = true )

        Separator(modifier = Modifier.
        padding(vertical = 3.dp).
        fillMaxWidth()
            .height(1.dp)
            .background(Color.Gray))

    }

}