package ru.simakover.to_docompose.navigation.destinations

import android.util.Log
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import ru.simakover.to_docompose.util.Action
import ru.simakover.to_docompose.util.Constants.LIST_ARGUMENT_KEY
import ru.simakover.to_docompose.util.Constants.LIST_SCREEN
import ru.simakover.to_docompose.util.Constants.TASK_ARGUMENT_KEY
import ru.simakover.to_docompose.util.Constants.TASK_SCREEN

fun NavGraphBuilder.taskComposable(
    navigateToListScreen: (Action) -> Unit
){
    composable(
        route = TASK_SCREEN,
        arguments = listOf(navArgument(TASK_ARGUMENT_KEY){type = NavType.IntType})
    ){ navBackStackEntry ->
        val taskId = navBackStackEntry.arguments!!.getInt(TASK_ARGUMENT_KEY)

    }
}