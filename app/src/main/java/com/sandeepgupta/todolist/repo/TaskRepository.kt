package com.sandeepgupta.todolist.repo

import com.sandeepgupta.todolist.models.DataItem
import com.sandeepgupta.todolist.models.TaskDao

class TaskRepository(private val taskDao: TaskDao) {

    fun getAllTask() = taskDao.getAllTask()

    suspend fun addTask(dataItem: DataItem) = taskDao.addTask(dataItem)

    suspend fun updateTask(dataItem: DataItem, b: Boolean) = taskDao.updateTask(dataItem.item,b)

    suspend fun deleteTask(dataItem: DataItem) = taskDao.deleteTask(dataItem)
}