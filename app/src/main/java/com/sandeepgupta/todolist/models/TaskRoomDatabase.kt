package com.sandeepgupta.todolist.models

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [(DataItem::class)], version = 1, exportSchema = false)
abstract class TaskRoomDatabase: RoomDatabase() {

    abstract fun taskDao():TaskDao

    companion object{

        @Volatile
        private var INSTANCE:TaskRoomDatabase?=null

        fun getInstance(context: Context):TaskRoomDatabase{
            synchronized(this){
                var instance = INSTANCE
                if(instance==null){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        TaskRoomDatabase::class.java,
                        "task_database"
                    ).fallbackToDestructiveMigration()
                        .build()

                    INSTANCE = instance
                }

                return instance
            }
        }
    }
}