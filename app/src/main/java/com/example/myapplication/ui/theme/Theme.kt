package com.example.myapplication

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable

private val LightColorScheme = lightColorScheme(
    background = PeachMaroon,
    primary = PastelMaroon,
    onPrimary = DarkMaroon,
    surface = WhiteCard,
    onSurface = DarkMaroon,
    outline = PastelMaroon
)

@Composable
fun GreetingAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = LightColorScheme,
        typography = AppTypography,
        content = content
    )
}