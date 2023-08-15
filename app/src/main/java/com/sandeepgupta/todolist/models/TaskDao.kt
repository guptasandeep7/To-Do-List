package com.sandeepgupta.todolist.models

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

const val TABLE_NAME = "ToDoList"

@Dao
interface TaskDao {
    @Insert
    suspend fun addTask(dataItem: DataItem)

    @Query("SELECT * FROM $TABLE_NAME")
    fun getAllTask(): LiveData<List<DataItem>>

    @Query("UPDATE $TABLE_NAME set isChecked=:b where id=:id")
    suspend fun updateTask(id: Int, b: Boolean)

    @Delete
    suspend fun deleteTask(dataItem: DataItem)
}