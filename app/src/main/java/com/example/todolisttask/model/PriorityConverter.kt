package com.example.todolisttask.model

import androidx.room.TypeConverter
import com.example.todolisttask.ext.Priority


class PriorityConverter {

    @TypeConverter
    fun fromPriority(priority: Priority): String {
        return priority.name }

    @TypeConverter
    fun toPriority(priorityString: String): Priority {
        return Priority.valueOf(priorityString) }
}