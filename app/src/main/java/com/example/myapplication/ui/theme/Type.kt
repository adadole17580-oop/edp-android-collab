package com.example.myapplication

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

val AppTypography = Typography(
    titleLarge = TextStyle(
        fontSize = 26.sp,
        fontWeight = FontWeight.Bold,
        color = DarkMaroon
    ),
    titleMedium = TextStyle(
        fontSize = 24.sp,
        fontWeight = FontWeight.Bold,
        color = DarkMaroon
    ),
    bodyLarge = TextStyle(
        fontSize = 18.sp,
        fontWeight = FontWeight.Medium,
        color = DarkMaroon
    ),
    bodyMedium = TextStyle(
        fontSize = 14.sp,
        color = DarkMaroon.copy(alpha = 0.7f)
    ),
    labelLarge = TextStyle(
        fontSize = 16.sp,
        fontWeight = FontWeight.SemiBold,
        color = DarkMaroon
    )
)