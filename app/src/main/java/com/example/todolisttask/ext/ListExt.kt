package com.example.todolisttask.ext

import com.example.todolisttask.model.Task
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId



fun List<Task>.groupByDate() = this.groupBy {
        Instant.ofEpochSecond(it.dueDate)
            .atZone(ZoneId.systemDefault())
            .toLocalDate()

}