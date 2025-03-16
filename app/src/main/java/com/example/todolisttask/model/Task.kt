package com.example.todolisttask.model

import android.os.Parcelable
import androidx.compose.runtime.Stable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.todolisttask.ext.Priority
import kotlinx.parcelize.Parcelize


@Parcelize
@Stable
@Entity
data class  Task constructor(
    @PrimaryKey(autoGenerate = true)
    val id :Long =0,
    val  title : String="",
    val description : String ="",
    val priority : Priority = Priority.NONE,
    val dueDate : Long = 0,
    val isCompleted : Boolean = false):Parcelable{

    fun hasValidTitle(): Boolean = title.isNotBlank() // Avoids empty & space-only titles


}