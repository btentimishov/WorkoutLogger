package com.baktyiar.ui_components.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.baktyiar.ui_components.theme.WorkoutLoggerTheme

@Composable
fun WorkoutCard(
    title: String,
    date: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = title, style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = date, style = MaterialTheme.typography.bodyMedium)
        }
    }
}

@Preview(name = "Light Mode", showBackground = true)
@Composable
fun WorkoutCardPreviewLight() {
    WorkoutLoggerTheme(darkTheme = false) {
        WorkoutCard(
            title = "Leg Day Workout",
            date = "2025-02-04",
            onClick = {}
        )
    }
}

@Preview(name = "Dark Mode", showBackground = true)
@Composable
fun WorkoutCardPreviewDark() {
    WorkoutLoggerTheme(darkTheme = true) {
        WorkoutCard(
            title = "Leg Day Workout",
            date = "2025-02-04",
            onClick = {}
        )
    }
}
