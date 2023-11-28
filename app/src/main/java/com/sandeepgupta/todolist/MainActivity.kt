package com.sandeepgupta.todolist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.sandeepgupta.todolist.ui.theme.ToDoListTheme
import com.sandeepgupta.todolist.view.MainUI
import com.sandeepgupta.todolist.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContent {
            ToDoListTheme {
                val navController: NavHostController = rememberNavController()
                MainUI(hiltViewModel<MainViewModel>(), navController)
            }
        }
    }
}



