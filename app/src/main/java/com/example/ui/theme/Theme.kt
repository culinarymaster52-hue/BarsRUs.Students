package com.example.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val BarCraftDarkColorScheme = darkColorScheme(
    primary = AmberGold,
    onPrimary = ObsidianBg,
    primaryContainer = AmberGoldDark,
    onPrimaryContainer = AmberGoldLight,
    secondary = CopperAccent,
    onSecondary = ObsidianBg,
    secondaryContainer = ObsidianCard,
    onSecondaryContainer = CopperLight,
    tertiary = AmberGoldLight,
    background = ObsidianBg,
    onBackground = TextPrimary,
    surface = ObsidianSurface,
    onSurface = TextPrimary,
    surfaceVariant = ObsidianSurfaceVariant,
    onSurfaceVariant = TextSecondary,
    outline = TextMuted,
    error = ErrorRed,
    onError = ObsidianBg
)

private val BarCraftLightColorScheme = BarCraftDarkColorScheme // Default to rich speakeasy dark ambience for authenticity

@Composable
fun BarCraftTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = BarCraftDarkColorScheme,
        typography = Typography,
        content = content
    )
}

@Composable
fun MyApplicationTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    BarCraftTheme(darkTheme = darkTheme, content = content)
}
