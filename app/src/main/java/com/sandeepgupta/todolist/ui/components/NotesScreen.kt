package com.sandeepgupta.todolist.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.sandeepgupta.todolist.viewmodels.NotesViewModel
import java.util.Random
import kotlin.random.asKotlinRandom

@Composable
fun NotesScreen() {

    val notesList = hiltViewModel<NotesViewModel>().notesList.observeAsState(emptyList())

    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Fixed(2),
        Modifier.padding(horizontal = 8.dp)
    ) {
        items(items = notesList.value) {
            NotesCard(title = it.title, body = it.body)
        }
    }
}

@Composable
fun NotesCard(title: String, body: String) {
    Card(
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .padding(8.dp)
            .fillMaxSize()
            .clickable(enabled = true) {

            },
    ) {
        Column(
            Modifier
                .padding(8.dp)
        ) {
            Text(
                text = title,
                fontSize = MaterialTheme.typography.titleLarge.fontSize,
                modifier = Modifier.padding(bottom = 8.dp),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            Text(
                text = body,
                fontSize = MaterialTheme.typography.bodySmall.fontSize,
                fontStyle = MaterialTheme.typography.bodySmall.fontStyle,
                maxLines = 5,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

fun getRandomColor(): Color {
    val rnd = Random().asKotlinRandom()
    return Color(255, rnd.nextInt(256), rnd.nextInt(256), 100)
}