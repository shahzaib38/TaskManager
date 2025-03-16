package com.example.todolisttask.filter

import com.example.todolisttask.menu.MenuOption


enum class Filter(override val optionName: String) : MenuOption {

    All("All"),
    PENDING("Pending"),
    COMPLETED("Completed")

}
