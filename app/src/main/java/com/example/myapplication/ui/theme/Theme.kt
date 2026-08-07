package com.example.myapplication.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable

private val LightColorScheme = lightColorScheme(
    background = PeachMaroon,
    primary = PastelMaroon,
    onPrimary = DarkMaroon,
    onBackground = DarkMaroon,
    onSurface = DarkMaroon
)

@Composable
fun GroceryTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = LightColorScheme,
        typography = AppTypography,
        content = content
    )
}