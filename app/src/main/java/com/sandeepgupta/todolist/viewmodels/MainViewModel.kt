package com.sandeepgupta.todolist.viewmodels

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sandeepgupta.todolist.models.DataItem
import com.sandeepgupta.todolist.models.TaskRoomDatabase
import com.sandeepgupta.todolist.repo.TaskRepository
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : ViewModel() {

    private val repository: TaskRepository
    val allTaskList: LiveData<List<DataItem>>

    init {
        val taskRoomDatabase = TaskRoomDatabase.getInstance(application)
        val taskDao = taskRoomDatabase.taskDao()
        repository = TaskRepository(taskDao)
        allTaskList = repository.getAllTask()
    }

    fun addItem(text: String) = viewModelScope.launch {
        repository.addTask(DataItem(text, false))
    }

    fun updateItem(id:Int, b: Boolean) = viewModelScope.launch {
        repository.updateTask(id,b)
    }

    fun deleteItem(dataItem: DataItem) = viewModelScope.launch {
        repository.deleteTask(dataItem)
    }

}