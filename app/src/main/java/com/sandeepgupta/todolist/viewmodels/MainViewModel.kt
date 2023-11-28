package com.sandeepgupta.todolist.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sandeepgupta.todolist.models.DataItem
import com.sandeepgupta.todolist.repo.TodoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val todoRepository: TodoRepository) : ViewModel() {

    val allTaskList: LiveData<List<DataItem>> = todoRepository.getAllTask()

    fun addItem(text: String) = viewModelScope.launch {
        todoRepository.addTask(DataItem(text, false))
    }

    fun updateItem(id: Int, b: Boolean) = viewModelScope.launch {
        todoRepository.updateTask(id, b)
    }

    fun deleteItem(dataItem: DataItem) = viewModelScope.launch {
        todoRepository.deleteTask(dataItem)
    }

}