package com.example.todolisttask

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.todolisttask.navigaiton.SetupNavGraph
import com.example.todolisttask.navigaiton.rememberTaskNavController
import com.example.todolisttask.ui.theme.TodoListTaskTheme
import com.example.todolisttask.viewmodel.TaskViewModel
import com.google.accompanist.systemuicontroller.SystemUiController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint




val LocalSystemUiController  = staticCompositionLocalOf<SystemUiController> {
    error("No SystemUiController provider") }


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    val taskViewModel by viewModels<TaskViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            TodoListTaskTheme(taskViewModel.isDarkTheme.value,
                primaryColor = taskViewModel.primaryColor.value) {

                val taskNavigation = rememberTaskNavController()
                val uiSystemController = rememberSystemUiController()
                CompositionLocalProvider(LocalSystemUiController provides uiSystemController) {
                    SetupNavGraph(
                        taskNavigation = taskNavigation,
                        taskViewModel = taskViewModel
                        )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TodoListTaskTheme {
        Greeting("Android")
    }
}