package com.example.todolisttask.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.todolisttask.dialog.ColorPickerDialog
import com.example.todolisttask.dialog.TaskDialogManager
import com.example.todolisttask.ext.PrimaryColorSelector
import com.example.todolisttask.ext.ThemeSwitch
import com.example.todolisttask.model.DialogType
import com.example.todolisttask.ui.theme.TodoListTaskTheme
import com.example.todolisttask.viewmodel.TaskViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun  SettingsScreen(taskViewModel: TaskViewModel ) {



        Scaffold(topBar = { TopAppBar( title = { Text("Settings") }) })
        { paddingValues ->

            SettingsView(
                modifier = Modifier.padding(paddingValues),
                isDarkTheme = taskViewModel.isDarkTheme,
                onThemeChange = taskViewModel::setTheme,
                primaryColor = taskViewModel.primaryColor,
                onPrimaryColorChange = taskViewModel::setPrimaryColor,
                taskViewModel
            )
        }
}

@Composable
fun dynamicColorScheme(isDarkTheme: Boolean, primary: Color): ColorScheme {
    return if (isDarkTheme) {
        darkColorScheme(primary = primary)
    } else {
        lightColorScheme(primary = primary)
    }
}


@Composable
fun SettingsView(
    modifier: Modifier,
    isDarkTheme: State<Boolean>,
    onThemeChange: (Boolean) -> Unit,
    primaryColor: State<Color>,
    onPrimaryColorChange: (Color) -> Unit,
    taskViewModel: TaskViewModel
    ) {

    val onColorPick = remember { {taskViewModel.setDialog(DialogType.ColorPicker)} }

    Box(modifier = modifier.fillMaxSize()){

        SettingsMainContent(
            isDarkTheme =isDarkTheme,
            onThemeChange = onThemeChange,
            primaryColor = primaryColor,
            onColorPick = onColorPick)

        TaskDialogManager(taskViewModel = taskViewModel)

    }
}


@Composable
fun SettingsMainContent(
    isDarkTheme: State<Boolean>,
                        onThemeChange: (Boolean) -> Unit,
                        primaryColor: State<Color>,
                        onColorPick:()->Unit){

    Column(modifier = Modifier.fillMaxWidth().padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)) {

        ThemeSwitch(
            isDarkTheme = isDarkTheme,
            onThemeChange = onThemeChange)


        PrimaryColorSelector(
            primaryColor = primaryColor,
            onColorPickerClick = onColorPick)

    }

}