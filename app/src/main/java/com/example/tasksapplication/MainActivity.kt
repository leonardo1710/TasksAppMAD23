package com.example.tasksapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.tasksapplication.models.Task
import com.example.tasksapplication.models.getTasks
import com.example.tasksapplication.ui.theme.TasksApplicationTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TasksApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val viewModel: TaskViewModel = viewModel()
                    val tasksState = viewModel.tasks.collectAsState()
                    // create a coroutine scope for suspend functions
                    val coroutineScope = rememberCoroutineScope()

                    Column {
                        AddTask(
                            onAddClick = {task -> coroutineScope.launch {
                                    viewModel.addTask(task)
                                }
                            }
                        )
                        TaskList(
                            tasks = tasksState.value,
                            onTaskChecked = {task -> coroutineScope.launch {
                                    viewModel.toggleDoneState(task)
                                }},
                            onTaskDone = {task -> coroutineScope.launch {
                                    viewModel.deleteTask(task)
                                }
                            }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun AddTask(
    onAddClick: (Task) -> Unit = {}
) {
    var label by remember {
        mutableStateOf("")
    }

    Row {
        OutlinedTextField(
            value = label,
            onValueChange = {label = it}
        )
        
        Button(onClick = {
            onAddClick(Task(label))
            label = ""
        }) {
            Text(text = "Add")
        }
    }
}

@Composable
fun TaskList(
    tasks: List<Task> = remember { getTasks() },
    onTaskChecked: (Task) -> Unit = {},
    onTaskDone: (Task) -> Unit = {}
){

    LazyColumn{
        items(items = tasks){ task ->
            TaskItem(
                taskName = task.label,
                checked = task.isDone,
                onCheckedChange = { onTaskChecked(task) },
                onClose = { onTaskDone(task) })
        }
    }
}

@Composable
fun TaskItem(
    taskName: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    onClose: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier
                .weight(1f)
                .padding(start = 16.dp),
            text = taskName
        )
        Checkbox(
            checked = checked,
            onCheckedChange = onCheckedChange
        )
        IconButton(onClick = onClose) {
            Icon(Icons.Filled.Close, contentDescription = "Close")
        }
    }
}