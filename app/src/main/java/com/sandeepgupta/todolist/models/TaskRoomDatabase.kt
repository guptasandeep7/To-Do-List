package com.sandeepgupta.todolist.models

import androidx.room.Database
import androidx.room.RoomDatabase

const val DATABASE_NAME = "task_database"

@Database(entities = [(DataItem::class), (NotesItem::class)], version = 6, exportSchema = false)
abstract class TaskRoomDatabase : RoomDatabase() {

    abstract fun todoDao(): TodoDao
    abstract fun notesDao(): NotesDao
}