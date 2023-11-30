package com.sandeepgupta.todolist.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sandeepgupta.todolist.models.NotesItem
import com.sandeepgupta.todolist.repo.NotesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotesViewModel @Inject constructor(private val notesRepository: NotesRepository,
    savedStateHandle: SavedStateHandle) :
    ViewModel() {

    val notesList: LiveData<List<NotesItem>> = notesRepository.getAllNotes()

    var currentId = MutableLiveData<Int>(null)
    var currentTitle = MutableLiveData("")

    var currentBody = MutableLiveData("")

//    init {
//        savedStateHandle.get<Int>("noteId").let{noteId->
//            if (noteId!=-1){
//                currentId.value = noteId
//                notesList.value?.find { it.id==noteId }.also { noteItem->
//                    currentTitle.value = noteItem?.title
//                    currentBody.value = noteItem?.body
//                }
//            }
//        }
//    }

    fun update(title: String? = currentTitle.value, body: String? = currentBody.value) =
        viewModelScope.launch {
            currentTitle.value = title
            currentBody.value = body
            autosaveWithDebounce(title!!, body!!)
        }

    fun deleteNote(noteItem: NotesItem) = viewModelScope.launch {
        notesRepository.deleteNote(noteItem)
    }

    // Autosave Logic with Debounce
    private suspend fun autosaveWithDebounce(title: String, body: String) {
        // Debounce for 500ms to avoid unnecessary updates
        val debouncedFlow = flow<Unit> {
            emit(Unit)
            delay(500)
        }.debounce(500)

        // Collect updates and trigger autosave
        debouncedFlow.collect {
            viewModelScope.launch {
                // Update the entity in the database
                notesRepository.addNote(NotesItem(id = currentId.value?:notesList.value?.size, title = title, body = body))

            }
        }
    }

}