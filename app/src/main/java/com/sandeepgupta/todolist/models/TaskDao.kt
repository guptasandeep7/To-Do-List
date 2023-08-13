package com.sandeepgupta.todolist.models

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

const val TABLE_NAME = "ToDoList"
@Dao
interface TaskDao {
    @Insert
    suspend fun addTask(dataItem: DataItem)

    @Query("SELECT * FROM $TABLE_NAME")
    fun getAllTask():LiveData<List<DataItem>>

    @Query("UPDATE $TABLE_NAME set isChecked=:b where task = :item")
    suspend fun updateTask(item:String, b:Boolean)

    @Delete
    suspend fun deleteTask(dataItem: DataItem)
}