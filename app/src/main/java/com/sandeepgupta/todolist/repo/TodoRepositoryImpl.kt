package com.sandeepgupta.todolist.repo

import com.sandeepgupta.todolist.models.TodoDao
import javax.inject.Inject

class TodoRepositoryImpl @Inject constructor(private val todoDao: TodoDao) {

}