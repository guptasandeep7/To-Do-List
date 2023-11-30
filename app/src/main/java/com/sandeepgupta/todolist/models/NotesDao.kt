package com.sandeepgupta.todolist.models

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update


@Dao
interface NotesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addNotes(notesItem: NotesItem)

    @Query("SELECT * FROM $NOTES_TABLE_NAME")
    fun getAllNotes(): LiveData<List<NotesItem>>

    @Update
    suspend fun updateNote(notesItem: NotesItem)

    @Delete
    suspend fun deleteNote(notesItem: NotesItem)
}