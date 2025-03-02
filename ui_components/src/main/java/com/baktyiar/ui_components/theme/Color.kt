package com.baktyiar.ui_components.theme


import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

val DarkPrimary = Color(0xFFCCCCCC)
val DarkOnPrimary = Color(0xFF000000)
val DarkSecondary = Color(0xFF999999)
val DarkOnSecondary = Color(0xFF000000)
val DarkBackground = Color(0xFF121212)
val DarkOnBackground = Color(0xFFFFFFFF)
val DarkSurface = Color(0xFF1E1E1E)
val DarkOnSurface = Color(0xFFDDDDDD)
val DarkError = Color(0xFFCF6679)

// Light Theme Colors
val LightPrimary = Color(0xFF333333)
val LightOnPrimary = Color(0xFFFFFFFF)
val LightSecondary = Color(0xFF666666)
val LightOnSecondary = Color(0xFFFFFFFF)
val LightBackground = Color(0xFFF5F5F5)
val LightOnBackground = Color(0xFF000000)
val LightSurface = Color(0xFFFFFFFF)
val LightOnSurface = Color(0xFF333333)
val LightError = Color(0xFFB00020)

val LightColors = lightColorScheme(
    primary = LightPrimary,
    onPrimary = LightOnPrimary,
    secondary = LightSecondary,
    onSecondary = LightOnSecondary,
    background = LightBackground,
    onBackground = LightOnBackground,
    surface = LightSurface,
    onSurface = LightOnSurface,
    error = LightError
)

val DarkColors = darkColorScheme(
    primary = DarkPrimary,
    onPrimary = DarkOnPrimary,
    secondary = DarkSecondary,
    onSecondary = DarkOnSecondary,
    background = DarkBackground,
    onBackground = DarkOnBackground,
    surface = DarkSurface,
    onSurface = DarkOnSurface,
    error = DarkError
)