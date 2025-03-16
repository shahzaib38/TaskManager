package com.example.todolisttask.sort

import com.example.todolisttask.menu.MenuOption


    enum class Sort(override val optionName: String) : MenuOption {

        PRIORITY("Priority"),
        ALPHABETICAL_ASCENDING("Alphabetical (A to Z)"),
        ALPHABETICAL_DESCENDING("Alphabetical (Z to A)"),
        DUE_NEWEST("Due (newest first)"),
        DUE_OLDEST("Due (oldest first)")

    }
