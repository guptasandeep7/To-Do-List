package com.sandeepgupta.todolist.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "ToDoList")
data class DataItem(

    @PrimaryKey(autoGenerate = true)
    val id: Int,

    @ColumnInfo(name = "item")
    val item: String,

    @ColumnInfo(name = "isChecked")
    var isChecked: Boolean
)

