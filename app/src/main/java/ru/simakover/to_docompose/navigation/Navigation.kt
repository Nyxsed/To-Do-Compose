package ru.simakover.to_docompose.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import ru.simakover.to_docompose.navigation.destinations.listComposable
import ru.simakover.to_docompose.navigation.destinations.splashComposable
import ru.simakover.to_docompose.navigation.destinations.taskComposable
import ru.simakover.to_docompose.ui.viewmodels.SharedViewModel
import ru.simakover.to_docompose.util.Constants.SPLASH_SCREEN

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
        startDestination = SPLASH_SCREEN
    ) {
        listComposable(
            navigateToTaskScreen = routes.toTaskScreen,
            sharedViewModel = sharedViewModel
        )
        taskComposable(
            sharedViewModel = sharedViewModel,
            navigateToListScreen = routes.toListScreen
        )
        splashComposable(
            navigateToListScreen = routes.fromSplashScreen
        )
    }
}