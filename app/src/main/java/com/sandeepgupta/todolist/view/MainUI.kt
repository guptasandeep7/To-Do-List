package com.sandeepgupta.todolist.view

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.sandeepgupta.todolist.ui.components.AddNotesScreen
import com.sandeepgupta.todolist.ui.components.AddTextField
import com.sandeepgupta.todolist.ui.components.NotesScreen
import com.sandeepgupta.todolist.ui.components.TodoScreen
import com.sandeepgupta.todolist.viewmodels.MainViewModel
import kotlinx.coroutines.launch


@OptIn(
    ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class,
    ExperimentalComposeUiApi::class
)
@Composable
fun MainUI(mainViewModel: MainViewModel, navController: NavHostController) {

    val keyboardController = LocalSoftwareKeyboardController.current
    val state = rememberModalBottomSheetState(ModalBottomSheetValue.Hidden)
    val scope = rememberCoroutineScope()

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    LaunchedEffect(state.currentValue) {
        if (state.currentValue == ModalBottomSheetValue.Hidden) {
            keyboardController?.hide()
        } else keyboardController?.show()
    }

    ModalBottomSheetLayout(
        sheetState = state,
        sheetContent = {
            AddTextField(mainViewModel = mainViewModel)
        },
        sheetShape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
        scrimColor = Color.Black.copy(alpha = 0.5f)
    ) {
        Scaffold(
            topBar = {
                TopAppBar(title = {
                    Text(
                        text = when (currentRoute) {
                            Destinations.Todo.route -> "Todo List"
                            Destinations.Notes.route -> "Notes"
                            Destinations.AddNotesScreen.route -> "Add Notes"
                            else -> "Todo List"
                        }
                    )
                })
            },
            floatingActionButton = {
                if (currentRoute != Destinations.AddNotesScreen.route)
                    FloatingActionButton(onClick = {
                        when (currentRoute) {
                            Destinations.Todo.route -> {
                                scope.launch {
                                    state.show()
                                    keyboardController?.show()
                                }
                            }

                            Destinations.Notes.route -> {
                                navController.navigate(Destinations.AddNotesScreen.route)
                            }
                        }
                    }) {
                        Icon(imageVector = Icons.Default.Add, contentDescription = "Add")
                    }
            },
            bottomBar = { BottomBar(navController = navController, currentRoute = currentRoute) },
            content = {
                Box(
                    modifier = Modifier.padding(it)
                ) {
                    NavigationGraph(navController = navController, mainViewModel)
                }

            }
        )
    }
}


@Composable
fun NavigationGraph(navController: NavHostController, mainViewModel: MainViewModel) {
    NavHost(navController,
        Destinations.Todo.route,
        enterTransition = { EnterTransition.None },
        exitTransition = { ExitTransition.None }
    ) {
        composable(
            Destinations.Todo.route,
            enterTransition = {
                slideInHorizontally() + fadeIn()
            },
            exitTransition = {
                slideOutHorizontally() + fadeOut()
            }
        ) {
            TodoScreen(mainViewModel = mainViewModel)
        }
        composable(Destinations.Notes.route,
            enterTransition = {
                fadeIn() + slideInHorizontally(
                    initialOffsetX = { it }
                )
            },
            exitTransition = {
                fadeOut() + slideOutHorizontally(
                    targetOffsetX = { it }
                )
            }) {
            NotesScreen()
        }
        composable(Destinations.AddNotesScreen.route) {
            AddNotesScreen()
        }
    }

}


@Composable
fun BottomBar(
    navController: NavHostController,
    currentRoute: String?
) {

    NavigationBar {

        screens.forEach { screen ->

            NavigationBarItem(
                label = {
                    Text(text = screen.title!!)
                },
                icon = {
                    Icon(imageVector = screen.icon!!, contentDescription = screen.title)
                },
                selected = currentRoute == screen.route,
                onClick = {
                    navController.navigate(screen.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedTextColor = MaterialTheme.colorScheme.primary
                ),
            )
        }
    }
}

