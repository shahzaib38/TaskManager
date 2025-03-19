package com.example.todolisttask.common

import android.util.Log
import androidx.compose.ui.graphics.Color
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

object Common {




     val colors = listOf(
         Color(android.graphics.Color.parseColor("#000000")),
         Color(android.graphics.Color.parseColor("#FFFF00")),
         Color(android.graphics.Color.parseColor("#FFA500")),
         Color(android.graphics.Color.parseColor("#FF0000")))




    fun mergeDateTime(dateMillis: Long, timeMillis: Long): Long {
        val dateCalendar = Calendar.getInstance().apply { timeInMillis = dateMillis }
        val timeCalendar = Calendar.getInstance().apply { timeInMillis = timeMillis }

        return Calendar.getInstance().apply {
            set(
                dateCalendar.get(Calendar.YEAR),
                dateCalendar.get(Calendar.MONTH),
                dateCalendar.get(Calendar.DAY_OF_MONTH),
                timeCalendar.get(Calendar.HOUR_OF_DAY),
                timeCalendar.get(Calendar.MINUTE),
                0
            )
        }.timeInMillis
    }






    //


    fun formatMillis(millis: Long): String {

        Log.i("CommonImpl" , "MillisSecond ${millis}")

        return  if (millis == 0L) {
            "Set Date"
        } else {
            val sdf = SimpleDateFormat("dd-MM-yyyy @ HH:mm", Locale.getDefault())
            sdf.format(Date(millis))
        }
        }




    //
}