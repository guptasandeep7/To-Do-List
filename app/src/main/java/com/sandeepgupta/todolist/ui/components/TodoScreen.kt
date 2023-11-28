package com.sandeepgupta.todolist.ui.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
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
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.sandeepgupta.todolist.models.DataItem
import com.sandeepgupta.todolist.viewmodels.MainViewModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TodoScreen(mainViewModel: MainViewModel) {

    val itemList = mainViewModel.allTaskList.observeAsState(initial = emptyList())

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
            items(items = list, key = { it.id }) { item ->
                ItemUI(
                    item = item,
                    mainViewModel = mainViewModel,
                    Modifier.animateItemPlacement()
                )
            }

            item {
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
            items(items = list2, key = { it.id }) { item ->
                ItemUI(
                    item = item,
                    mainViewModel = mainViewModel,
                    Modifier.animateItemPlacement()
                )
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
                    mainViewModel.updateItem(item.id, it)
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
            .background(MaterialTheme.colorScheme.background),

        ) {
        TextField(
            modifier = Modifier
                .weight(1f),
            value = text,
            onValueChange = { text = it },
            placeholder = { Text(text = "Add a task") },
            maxLines = 1,
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(
                onDone = {
                    addItem()
                }
            )
        )
//
//        Button(
//            onClick = { addItem() },
//            modifier = Modifier
//                .padding(horizontal = 4.dp)
//        ) {
//            Text(text = "Add")
//
//        }

    }

}