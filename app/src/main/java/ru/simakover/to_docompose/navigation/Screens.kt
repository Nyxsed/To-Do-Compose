package ru.simakover.to_docompose.navigation

import androidx.navigation.NavHostController
import ru.simakover.to_docompose.util.Action
import ru.simakover.to_docompose.util.Constants.LIST_SCREEN
import ru.simakover.to_docompose.util.Constants.TASK_SCREEN

class Screens(navController: NavHostController) {
    val list: (Action) -> Unit = { action ->
        navController.navigate("list/${action.name}") {
            popUpTo(LIST_SCREEN) {inclusive = true}
        }
    }

    val task: (Int) -> Unit = {taskId ->
        navController.navigate("task/$taskId")
    }
}