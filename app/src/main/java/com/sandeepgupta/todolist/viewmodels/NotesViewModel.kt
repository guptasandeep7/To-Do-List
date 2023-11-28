package com.sandeepgupta.todolist.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sandeepgupta.todolist.models.NotesItem
import com.sandeepgupta.todolist.repo.NotesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotesViewModel @Inject constructor(private val notesRepository: NotesRepository) :
    ViewModel() {

    val notesList: LiveData<List<NotesItem>> = notesRepository.getAllNotes()

    fun addNote(title: String, body: String) = viewModelScope.launch {
        notesRepository.addNote(NotesItem(title, body))
    }

    fun updateNote(id: Int, title: String, body: String) = viewModelScope.launch {
        notesRepository.updateNote(id, title, body)
    }

    fun deleteNote(noteItem: NotesItem) = viewModelScope.launch {
        notesRepository.deleteNote(noteItem)
    }
}