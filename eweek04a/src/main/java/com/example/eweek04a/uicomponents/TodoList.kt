package com.example.eweek04a.uicomponents

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.eweek04a.model.Item
import com.example.eweek04a.model.TodoItemFactory
import com.example.eweek04a.model.TodoStatus

@Composable
fun TodoList(todoList: SnapshotStateList<Item>, modifier: Modifier = Modifier) {
    val scrollState = rememberScrollState()
    Column(
        modifier = modifier
            .fillMaxSize() // Key change: Add fillMaxSize()
            .fillMaxWidth()
            .verticalScroll(scrollState)
    ) {
        todoList.forEachIndexed { index, item ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp)
            ) {
                Row {
                    TodoCheckbox(checked = item.status == TodoStatus.COMPLETED) { isChecked ->
                        todoList[index] =
                            item.copy(status = if (isChecked) TodoStatus.COMPLETED else TodoStatus.PENDING)
                    }
                    TodoItem(item = item)
                }
            }
        }
    }
}

@Preview
@Composable
private fun TodoListPreview() {
    TodoList(TodoItemFactory.makeTodoList())
}