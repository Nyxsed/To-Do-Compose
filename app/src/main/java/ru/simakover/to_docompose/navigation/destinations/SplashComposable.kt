package ru.simakover.to_docompose.navigation.destinations

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import ru.simakover.to_docompose.ui.screens.splash.SplashScreen
import ru.simakover.to_docompose.util.Constants.SPLASH_SCREEN

fun NavGraphBuilder.splashComposable(
    navigateToListScreen: () -> Unit,
) {
    composable(
        route = SPLASH_SCREEN,
    ) {

        SplashScreen(navigateToListScreen)
    }
}