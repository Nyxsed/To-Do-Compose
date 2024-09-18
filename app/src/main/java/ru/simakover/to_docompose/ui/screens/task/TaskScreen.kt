package ru.simakover.to_docompose.ui.screens.task

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import ru.simakover.to_docompose.data.models.Priority
import ru.simakover.to_docompose.data.models.ToDoTask
import ru.simakover.to_docompose.util.Action

@Composable
fun TaskScreen(
    selectedTask: ToDoTask?,
    navigateToListScreen: (Action) -> Unit
) {
    Scaffold(
        topBar = {
            TaskAppBar(
                selectedTask,
                navigateToListScreen
            )
        },
        content = { padding ->

        }
    )
}