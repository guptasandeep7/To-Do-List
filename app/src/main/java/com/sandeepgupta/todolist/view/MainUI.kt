package com.sandeepgupta.todolist.view

import android.app.Application
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sandeepgupta.todolist.R
import com.sandeepgupta.todolist.models.DataItem
import com.sandeepgupta.todolist.ui.theme.ToDoListTheme
import com.sandeepgupta.todolist.viewmodels.MainViewModel

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun MainUI(mainViewModel: MainViewModel) {

    val itemList = mainViewModel.allTaskList.observeAsState(initial = emptyList())

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        TopAppBar(title = {
            Row(
                horizontalArrangement = Arrangement.Center
            ) {
                Text(text = stringResource(id = R.string.app_name))
            }
        })
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {

            LazyColumn(
                modifier = Modifier
                    .weight(1f)
            ) {
                val list = itemList.value.filter {
                    !it.isChecked
                }
                items(items = list, key = { it.item }) { item ->
                    ItemUI(
                        item = item,
                        mainViewModel = mainViewModel,
                        Modifier.animateItemPlacement()
                    )
                }

                item(1) {
                    Divider(
                        modifier = Modifier
                            .padding(vertical = 8.dp),
                        thickness = 2.dp,
                        color = Color.Gray
                    )
                }

                val list2 = itemList.value.filter {
                    it.isChecked
                }
                items(items = list2, key = { it.item }) { item ->
                    ItemUI(
                        item = item,
                        mainViewModel = mainViewModel,
                        Modifier.animateItemPlacement()
                    )
                }
            }

            Box(
                contentAlignment = Alignment.BottomCenter
            ) {
                AddTextField(mainViewModel)
            }
        }

    }
}

@Composable
fun ItemUI(item: DataItem, mainViewModel: MainViewModel, modifier: Modifier) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        Row(
            modifier = Modifier
                .weight(1f)
                .height(56.dp)
                .toggleable(
                    value = item.isChecked
                ) {
                    mainViewModel.updateItem(item, it)
                }
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = item.isChecked,
                onCheckedChange = null
            )
            if (item.isChecked)
                Text(
                    text = item.item,
                    style = MaterialTheme.typography.bodyLarge,
                    textDecoration = TextDecoration.LineThrough,
                    modifier = Modifier.padding(start = 16.dp)
                )
            else
                Text(
                    text = item.item,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(start = 16.dp)
                )
        }
        IconButton(onClick = { mainViewModel.deleteItem(item) }) {
            Icon(
                Icons.Default.Delete,
                contentDescription = "Delete",
                modifier = Modifier.padding(8.dp)
            )
        }

    }
}

@Composable
fun AddTextField(mainViewModel: MainViewModel) {
    var text by remember {
        mutableStateOf("")
    }

    fun addItem() {
        mainViewModel.addItem(text)
        text = ""
    }

    Row(
        Modifier
            .padding(vertical = 16.dp, horizontal = 8.dp)
    ) {
        TextField(
            modifier = Modifier
                .weight(1f),
            value = text,
            onValueChange = { text = it },
            placeholder = { Text(text = "Enter the text") },
            maxLines = 1,
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(
                onDone = {
                    addItem()
                }
            )
        )

        Button(
            onClick = { addItem() },
            modifier = Modifier
                .padding(horizontal = 4.dp)
        ) {
            Text(text = "Add")

        }

    }

}

@Preview
@Composable
fun GreetingPreview() {
    ToDoListTheme {
        val application = Application()
        val mainViewModel = MainViewModel(application)
        MainUI(mainViewModel)
    }
}