package com.sandeepgupta.todolist.repo

import com.sandeepgupta.todolist.models.NotesDao
import com.sandeepgupta.todolist.models.NotesItem
import javax.inject.Inject

class NotesRepository @Inject constructor(private val notesDao: NotesDao) {

    fun getAllNotes() = notesDao.getAllNotes()

    suspend fun addNote(notesItem: NotesItem) = notesDao.addNotes(notesItem)

    suspend fun updateNote(notesItem: NotesItem) =
        notesDao.updateNote(notesItem)

    suspend fun deleteNote(notesItem: NotesItem) = notesDao.deleteNote(notesItem)
}