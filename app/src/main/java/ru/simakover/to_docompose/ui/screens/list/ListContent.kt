package ru.simakover.to_docompose.ui.screens.list

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ru.simakover.to_docompose.R
import ru.simakover.to_docompose.data.models.Priority
import ru.simakover.to_docompose.data.models.ToDoTask
import ru.simakover.to_docompose.ui.theme.HighPriorityColor
import ru.simakover.to_docompose.ui.theme.LARGEST_PADDING
import ru.simakover.to_docompose.ui.theme.LARGE_PADDING
import ru.simakover.to_docompose.ui.theme.PRIORITY_INDICATOR_SIZE
import ru.simakover.to_docompose.util.Action
import ru.simakover.to_docompose.util.RequestState
import ru.simakover.to_docompose.util.SearchAppBarState

@Composable
fun ListContent(
    allTasks: RequestState<List<ToDoTask>>,
    searchedTasks: RequestState<List<ToDoTask>>,
    lowPriorityTasks: List<ToDoTask>,
    highPriorityTasks: List<ToDoTask>,
    sortState: RequestState<Priority>,
    searchAppBarState: SearchAppBarState,
    navigateToTaskScreen: (taskId: Int) -> Unit,
    onSwipeToDelete: (Action, ToDoTask) -> Unit,
) {
    if (sortState is RequestState.Success) {
        when {
            searchAppBarState == SearchAppBarState.TRIGGERED -> {
                if (searchedTasks is RequestState.Success) {
                    HandleListContent(tasks = searchedTasks.data, navigateToTaskScreen, onSwipeToDelete)
                }
            }

            sortState.data == Priority.NONE -> {
                if (allTasks is RequestState.Success) {
                    HandleListContent(tasks = allTasks.data, navigateToTaskScreen, onSwipeToDelete)
                }
            }

            sortState.data == Priority.LOW -> {
                HandleListContent(tasks = lowPriorityTasks, navigateToTaskScreen, onSwipeToDelete)
            }

            sortState.data == Priority.HIGH -> {
                HandleListContent(tasks = highPriorityTasks, navigateToTaskScreen, onSwipeToDelete)
            }
        }
    }
}

@Composable
fun HandleListContent(
    tasks: List<ToDoTask>,
    navigateToTaskScreen: (taskId: Int) -> Unit,
    onSwipeToDelete: (Action, ToDoTask) -> Unit,
) {
    when (tasks) {
        emptyList<ToDoTask>() -> {
            EmptyContent()
        }

        else -> {
            DisplayTasks(
                tasks,
                navigateToTaskScreen,
                onSwipeToDelete
            )
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DisplayTasks(
    tasks: List<ToDoTask>,
    navigateToTaskScreen: (taskId: Int) -> Unit,
    onSwipeToDelete: (Action, ToDoTask) -> Unit,
) {
    LazyColumn {
        items(
            items = tasks,
            key = { task ->
                task.id
            }
        ) { task ->

            val dismissState = rememberSwipeToDismissBoxState()
            val dismissDirection = dismissState.dismissDirection
            val isDismissed =
                dismissState.dismissDirection == SwipeToDismissBoxValue.EndToStart && dismissState.progress == 1f

            if (isDismissed && dismissDirection == SwipeToDismissBoxValue.EndToStart) {
                val scope = rememberCoroutineScope()
                SideEffect {
                    scope.launch {
                        delay(200)
                        onSwipeToDelete(Action.DELETE,task)
                    }
                }
            }

            val degrees by animateFloatAsState(
                if (dismissState.progress in 0f..0.5f) 0f else -45f,
                label = "Degree animation"
            )

            SwipeToDismissBox(
                state = dismissState,
                enableDismissFromEndToStart = true,
                enableDismissFromStartToEnd = false,
                backgroundContent = { RedBackground(degrees) }
            ) {
                TaskItem(
                    toDoTask = task,
                    navigateToTaskScreen = navigateToTaskScreen
                )
            }
        }
    }
}

@Composable
fun TaskItem(
    toDoTask: ToDoTask,
    navigateToTaskScreen: (taskId: Int) -> Unit,
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth(),
        color = MaterialTheme.colorScheme.surface,
        shape = RectangleShape,
        onClick = {
            navigateToTaskScreen(toDoTask.id)
        }
    ) {
        Column(
            modifier = Modifier
                .padding(all = LARGE_PADDING)
                .fillMaxWidth()
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = Modifier
                        .weight(8f),
                    text = toDoTask.title,
                    color = Color.DarkGray,
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                )
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    contentAlignment = Alignment.CenterEnd,

                    ) {
                    Canvas(
                        modifier = Modifier
                            .width(PRIORITY_INDICATOR_SIZE)
                            .height(PRIORITY_INDICATOR_SIZE)
                    ) {
                        drawCircle(
                            color = toDoTask.priority.color
                        )
                    }
                }
            }
            Text(
                modifier = Modifier
                    .fillMaxWidth(),
                text = toDoTask.description,
                color = Color.DarkGray,
                style = MaterialTheme.typography.bodyMedium,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Composable
fun RedBackground(
    degrees: Float,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(HighPriorityColor)
            .padding(horizontal = LARGEST_PADDING),
        contentAlignment = Alignment.CenterEnd
    ) {
        Icon(
            modifier = Modifier
                .rotate(degrees = degrees),
            imageVector = Icons.Filled.Delete,
            contentDescription = stringResource(id = R.string.delete_task),
            tint = Color.White
        )
    }
}

@Preview
@Composable
fun TaskItemPreview() {
    TaskItem(
        toDoTask = ToDoTask(
            id = -1,
            title = "Some task",
            description = "Some description asd asdas dasda sdsa dsdasdd asdasdsadasda sdasdasdasdasasdasdasdasdasdasdasdasdasd",
            priority = Priority.LOW
        ),
        navigateToTaskScreen = {}
    )
}
