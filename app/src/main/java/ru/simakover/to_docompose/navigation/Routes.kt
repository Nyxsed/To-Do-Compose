package ru.simakover.to_docompose.navigation

import androidx.navigation.NavHostController
import ru.simakover.to_docompose.util.Action
import ru.simakover.to_docompose.util.Constants.LIST_SCREEN
import ru.simakover.to_docompose.util.Constants.SPLASH_SCREEN

class Routes(navController: NavHostController) {
    val toListScreen: (Action) -> Unit = { action ->
        navController.navigate("list/${action.name}") {
            popUpTo(LIST_SCREEN) {inclusive = true}
        }
    }

    val toTaskScreen: (Int) -> Unit = { taskId ->
        navController.navigate("task/$taskId")
    }

    val fromSplashScreen: () -> Unit = {
        navController.navigate("list/${Action.NO_ACTION.name}") {
            popUpTo(SPLASH_SCREEN) {inclusive = true}
        }
    }
}