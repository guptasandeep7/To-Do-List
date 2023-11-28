package com.sandeepgupta.todolist.models

import androidx.room.Entity
import androidx.room.PrimaryKey

const val NOTES_TABLE_NAME = "NotesTable"

@Entity(tableName = NOTES_TABLE_NAME)
data class NotesItem(
    val title: String,
    val body: String
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}