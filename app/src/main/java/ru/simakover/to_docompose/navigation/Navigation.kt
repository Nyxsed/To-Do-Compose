package ru.simakover.to_docompose.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import ru.simakover.to_docompose.navigation.destinations.listComposable
import ru.simakover.to_docompose.navigation.destinations.taskComposable
import ru.simakover.to_docompose.ui.viewmodels.SharedViewModel
import ru.simakover.to_docompose.util.Constants.LIST_SCREEN
import javax.inject.Inject

@Composable
fun SetupNavigation(
    navController: NavHostController,
    sharedViewModel: SharedViewModel
) {
    val routes = remember(navController) {
        Routes(navController)
    }

    NavHost(
        navController = navController,
        startDestination = LIST_SCREEN
    ) {
        listComposable(
            navigateToTaskScreen = routes.toTaskScreen,
            sharedViewModel = sharedViewModel
        )
        taskComposable(
            navigateToListScreen = routes.toListScreen
        )
    }
}