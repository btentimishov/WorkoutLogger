package com.baktyiar.ui_components.components

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WorkoutTopAppBar(
    title: @Composable () -> Unit,
    navigationIcon: (@Composable () -> Unit) = { },
    actions: (@Composable RowScope.() -> Unit) = { }
) {
    TopAppBar(
        title = title,
        navigationIcon = navigationIcon,
        actions = actions
    )
}