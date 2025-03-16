package com.example.todolisttask.ext

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.FocusInteraction
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp


@Composable
fun  QuickAddTask(onFabClick :(String)->Unit ){


    var taskText by   remember { mutableStateOf("") }

    Row(modifier = Modifier.fillMaxWidth()
        .wrapContentHeight()
        .padding(8.dp ),
        verticalAlignment = Alignment.CenterVertically){

        TaskInputField(text = taskText , onTextChange = { change ->
            taskText = change },
            placeHolderText = "Quick add task...",
            modifier = Modifier
                .weight(1f)
                .padding(end = 8.dp)
                .background(
                    Color.LightGray, shape = RoundedCornerShape(20.dp)))

        SendTaskButton(
            isEnabled = taskText.isNotEmpty(),
            onClick = { onFabClick(taskText) })
    }

}

@Composable
fun TaskInputField(text: String,
                   onTextChange :(String)->Unit,
                            colors : TextFieldColors =  TextFieldDefaults.colors(
                                disabledContainerColor = Color.Transparent,
                                disabledIndicatorColor = Color.Transparent,
                                focusedIndicatorColor = Color.Transparent,
                                unfocusedIndicatorColor = Color.Transparent,
                                focusedContainerColor = Color.Transparent),
                            keyboardOptions: KeyboardOptions = KeyboardOptions.Default.copy(
                            imeAction = ImeAction.Done),
                            modifier : Modifier = Modifier,
                            keyboardActions: KeyboardActions = KeyboardActions(
                                onDone = {

                                }
                            ),
                            placeHolderText : String,
                   contentPadding : PaddingValues = PaddingValues(0.dp)
){

    val interactionSource = remember { MutableInteractionSource() }
//    val focusManager = LocalFocusManager.current

    TextField(

        interactionSource  = interactionSource,
        value = text,
        onValueChange = onTextChange,
        placeholder = { Text(placeHolderText) },
        modifier = modifier ,
        singleLine = true,
        colors = colors,
        keyboardOptions = keyboardOptions,
        keyboardActions =  keyboardActions)


}


//

//
//@Composable
//fun keyboardAsState(): State<Boolean> {
//    val isImeVisible = WindowInsets.ime.getBottom(LocalDensity.current) > 0
//    return rememberUpdatedState(isImeVisible)
//}

@Composable
fun keyboardAsState(): State<Boolean> {
    val imeVisible = WindowInsets.ime.getBottom(LocalDensity.current) > 0
    val keyboardState = remember { mutableStateOf(imeVisible) }

    LaunchedEffect(imeVisible) {
        if (keyboardState.value != imeVisible) {
            keyboardState.value = imeVisible
        }
    }

    return keyboardState
}

//

@Composable
fun SendTaskButton(isEnabled: Boolean, onClick: () -> Unit) {
    IconButton(
        onClick = onClick,
        enabled = isEnabled
    ) {
        Icon(
            imageVector = Icons.Default.Send,
            contentDescription = "Send Task"
        )
    }
}