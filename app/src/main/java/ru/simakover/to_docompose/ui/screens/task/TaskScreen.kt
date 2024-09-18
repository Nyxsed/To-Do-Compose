package ru.simakover.to_docompose.ui.screens.task

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import ru.simakover.to_docompose.util.Action

@Composable
fun TaskScreen(
    navigateToListScreen: (Action) -> Unit
) {
    Scaffold(
        topBar = {
            TaskAppBar(
                navigateToListScreen
            )
        },
        content = { padding ->

        }
    )
}


@Preview
@Composable
fun TaskScreenPreview() {
    TaskScreen(
        navigateToListScreen = {},
    )
}