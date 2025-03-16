package com.example.todolisttask.topbar

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.example.todolisttask.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(title:String ="Task Manager",
           onMenuClick : ()->Unit,
           filterClick : ()->Unit
           ){

    TopAppBar(modifier = Modifier.fillMaxWidth().wrapContentHeight(),
        title = {
            Text(text =title)

        },
        actions = {

            IconButton(onClick = filterClick) {

                Icon(
                    painter = painterResource(R.drawable.filter_icon),
                    contentDescription = "Filter") }


            IconButton(onClick = onMenuClick) {
                Icon(
                    imageVector = Icons.Default.MoreVert,
                    contentDescription = "Menu"
                )
            }

        }
    )

}