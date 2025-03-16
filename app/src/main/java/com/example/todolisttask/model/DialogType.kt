package com.example.todolisttask.model


sealed class DialogType {
    data object None : DialogType()
    data object TaskMenu : DialogType()
    data object DatePicker : DialogType()
    data object TimePicker : DialogType()
    data object SortMenu : DialogType()
    data object Filter : DialogType()
    data object ColorPicker : DialogType()

}