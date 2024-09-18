package ru.simakover.to_docompose.navigation.destinations

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import ru.simakover.to_docompose.ui.screens.task.TaskScreen
import ru.simakover.to_docompose.util.Action
import ru.simakover.to_docompose.util.Constants.TASK_ARGUMENT_KEY
import ru.simakover.to_docompose.util.Constants.TASK_SCREEN

fun NavGraphBuilder.taskComposable(
    navigateToListScreen: (Action) -> Unit
) {
    composable(
        route = TASK_SCREEN,
        arguments = listOf(navArgument(TASK_ARGUMENT_KEY) { type = NavType.IntType })
    ) { navBackStackEntry ->
        val taskId = navBackStackEntry.arguments!!.getInt(TASK_ARGUMENT_KEY)

        TaskScreen(
            navigateToListScreen
        )
    }
}