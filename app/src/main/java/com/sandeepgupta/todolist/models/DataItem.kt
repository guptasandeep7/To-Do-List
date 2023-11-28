package com.sandeepgupta.todolist.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

const val TODO_TABLE_NAME = "ToDoList"

@Entity(tableName = TODO_TABLE_NAME)
data class DataItem(
    @ColumnInfo(name = "item")
    val item: String,

    @ColumnInfo(name = "isChecked")
    var isChecked: Boolean
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}

