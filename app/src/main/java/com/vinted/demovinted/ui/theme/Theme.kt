package com.vinted.demovinted.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable

private val AppColorScheme = lightColorScheme(
    primary = Primary,
    onPrimary = OnPrimary,
    primaryContainer = PrimaryDark,
    outline = Outline,
    onSurface = OnSurface,
)

@Composable
fun AppTheme(
    content: @Composable () -> Unit,
) {
    MaterialTheme(
        colorScheme = AppColorScheme,
        content = content,
    )
}

object AppTheme {
    @Composable
    @ReadOnlyComposable
    fun colorScheme() = MaterialTheme.colorScheme
}
