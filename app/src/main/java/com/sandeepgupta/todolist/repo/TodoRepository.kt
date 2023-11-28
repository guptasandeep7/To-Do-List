package com.sandeepgupta.todolist.repo

import com.sandeepgupta.todolist.models.DataItem
import com.sandeepgupta.todolist.models.TodoDao
import javax.inject.Inject

class TodoRepository @Inject constructor(private val todoDao: TodoDao) {

    fun getAllTask() = todoDao.getAllTask()

    suspend fun addTask(dataItem: DataItem) = todoDao.addTask(dataItem)

    suspend fun updateTask(id: Int, b: Boolean) = todoDao.updateTask(id, b)

    suspend fun deleteTask(dataItem: DataItem) = todoDao.deleteTask(dataItem)
}