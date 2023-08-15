package com.sandeepgupta.todolist.repo

import com.sandeepgupta.todolist.models.DataItem
import com.sandeepgupta.todolist.models.TaskDao

class TaskRepository(private val taskDao: TaskDao) {

    fun getAllTask() = taskDao.getAllTask()

    suspend fun addTask(dataItem: DataItem) = taskDao.addTask(dataItem)

    suspend fun updateTask(id:Int,b:Boolean) = taskDao.updateTask(id,b)

    suspend fun deleteTask(dataItem: DataItem) = taskDao.deleteTask(dataItem)
}