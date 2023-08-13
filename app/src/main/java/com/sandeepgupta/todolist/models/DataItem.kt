package com.sandeepgupta.todolist.models

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "ToDoList")
data class DataItem(

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "task")
    val item: String,

    @ColumnInfo(name = "isChecked")
    var isChecked: Boolean
)

