package com.baktyiar.ui_components.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.platform.LocalView
import androidx.core.view.ViewCompat

@Composable
fun WorkoutLoggerTheme(
    darkTheme: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColors else LightColors

    val view = LocalView.current
    SideEffect {
        ViewCompat.getWindowInsetsController(view)?.isAppearanceLightStatusBars = !darkTheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        content = content,
        typography = Typography,
        shapes = Shapes
    )
}