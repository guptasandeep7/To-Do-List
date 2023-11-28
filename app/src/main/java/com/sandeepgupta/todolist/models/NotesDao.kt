package com.sandeepgupta.todolist.models

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query


@Dao
interface NotesDao {
    @Insert
    suspend fun addNotes(notesItem: NotesItem)

    @Query("SELECT * FROM $NOTES_TABLE_NAME")
    fun getAllNotes(): LiveData<List<NotesItem>>

    @Query("UPDATE $NOTES_TABLE_NAME set title=:title AND body=:body where id=:id")
    suspend fun updateNote(id: Int, title: String, body: String)

    @Delete
    suspend fun deleteNote(notesItem: NotesItem)
}