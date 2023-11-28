package com.sandeepgupta.todolist.view

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.List
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Destinations(
    val route: String,
    val title: String? = null,
    val icon: ImageVector? = null
) {
    object Todo : Destinations(
        route = "todo_screen",
        title = "Todo",
        icon = Icons.Outlined.List
    )

    object Notes : Destinations(
        route = "notes_screen",
        title = "Notes",
        icon = Icons.Outlined.Notifications
    )

    object AddNotesScreen : Destinations(
        route = "add_notes_screen",
        title = "Add Notes",
        icon = Icons.Outlined.Notifications
    )

}

val screens = listOf(
    Destinations.Todo, Destinations.Notes
)
