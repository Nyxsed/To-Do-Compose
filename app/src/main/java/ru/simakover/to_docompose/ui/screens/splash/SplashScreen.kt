package ru.simakover.to_docompose.ui.screens.splash

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.coroutines.delay
import ru.simakover.to_docompose.R
import ru.simakover.to_docompose.ui.theme.LOGO_SIZE
import ru.simakover.to_docompose.util.Constants.SPLASH_SCREEN_DELAY

@Composable
fun SplashScreen(
    navigateToListScreen: () -> Unit
) {
    LaunchedEffect(key1 = true) {
        delay(SPLASH_SCREEN_DELAY)
        navigateToListScreen()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primary),
        contentAlignment = Alignment.Center
    ) {
        Image(
            modifier = Modifier
                .size(LOGO_SIZE),
            painter = painterResource(id = R.drawable.logo_light),
            contentDescription = stringResource(R.string.logo)
        )
    }
}

@Composable
@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO or Configuration.UI_MODE_TYPE_NORMAL)
fun SplashScreenPreview() {
    SplashScreen({})
}