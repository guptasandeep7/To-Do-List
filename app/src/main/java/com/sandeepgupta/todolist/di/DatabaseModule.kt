package com.sandeepgupta.todolist.di

import android.content.Context
import androidx.room.Room
import com.sandeepgupta.todolist.models.DATABASE_NAME
import com.sandeepgupta.todolist.models.NotesDao
import com.sandeepgupta.todolist.models.TaskRoomDatabase
import com.sandeepgupta.todolist.models.TodoDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {

    @Provides
    fun provideTodoDao(todoRoomDatabase: TaskRoomDatabase): TodoDao {
        return todoRoomDatabase.todoDao()
    }

    @Provides
    fun provideNotesDao(todoRoomDatabase: TaskRoomDatabase): NotesDao {
        return todoRoomDatabase.notesDao()
    }

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext applicationContext: Context): TaskRoomDatabase {
        return Room.databaseBuilder(
            applicationContext,
            TaskRoomDatabase::class.java, DATABASE_NAME
        ).fallbackToDestructiveMigration()
            .build()
    }


}