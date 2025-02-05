package com.baktyiar.ui_components.components

import android.annotation.SuppressLint
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@SuppressLint("DefaultLocale")
@Composable
fun TimerDisplay(
    elapsedTimeSeconds: Long
) {
    val minutes = elapsedTimeSeconds / 60
    val seconds = elapsedTimeSeconds % 60
    Text(text = String.format("%02d:%02d", minutes, seconds))
}