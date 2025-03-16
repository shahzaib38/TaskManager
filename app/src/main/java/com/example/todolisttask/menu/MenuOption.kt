package com.example.todolisttask.menu



interface MenuOption {
    val optionName: String
}


enum class TaskMenuOption(override val optionName: String) : MenuOption {
    SORT_BY("Sort by"),
    SETTINGS("Settings")

}


