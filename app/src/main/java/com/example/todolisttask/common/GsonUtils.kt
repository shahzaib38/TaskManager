package com.example.todolisttask.common

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


object GsonUtils {
     val gson = Gson()

    fun <T> toJson(data: T): String {
        return gson.toJson(data) }

     inline fun <reified T> fromJson(json: String?): T? {
        return json?.let {
            gson.fromJson(it, object : TypeToken<T>() {}.type)
        }
    }
}