package com.sandeepgupta.todolist.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.sandeepgupta.todolist.viewmodels.NotesViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddNotesScreen() {

    val notesViewModel = hiltViewModel<NotesViewModel>()

    var title by remember {
        mutableStateOf("")
    }

    var body by remember {
        mutableStateOf("")
    }

    fun saveNote() {
        notesViewModel.addNote(title, body)
    }

    Scaffold {
        Column(
            Modifier
                .padding(it)
                .padding(horizontal = 16.dp)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {

            Spacer(modifier = Modifier.height(16.dp))
            BasicTextField(
                value = title,
                onValueChange = { title = it },
                textStyle = TextStyle(
                    fontSize = MaterialTheme.typography.titleLarge.fontSize,
                    color = MaterialTheme.colorScheme.onSurface
                ),
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Transparent)
                    .padding(vertical = 8.dp),
                decorationBox = { innerTextField ->
                    if (title.isEmpty()) {
                        Text(
                            text = "Title",
                            fontSize = MaterialTheme.typography.titleLarge.fontSize,
                            color = Color.Gray
                        )
                    }
                    innerTextField()
                }
            )

            Spacer(modifier = Modifier.height(24.dp))

            BasicTextField(
                value = body,
                onValueChange = { body = it },
                textStyle = TextStyle(
                    fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                    color = MaterialTheme.colorScheme.onSurface
                ),
                decorationBox = { innerTextField ->
                    if (body.isEmpty()) {
                        Text(
                            text = "Write some text ...",
                            fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                            color = Color.Gray,
                            textAlign = TextAlign.Start,
                            modifier = Modifier.fillMaxSize()
                        )
                    }
                    innerTextField()
                },
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Transparent)
            )

            Button(onClick = { saveNote() }) {
                Text(text = "Save")
            }

        }

    }
}