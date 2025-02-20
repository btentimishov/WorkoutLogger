package com.baktyiar.ui_components.components.workout

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.baktyiar.ui_components.model.WorkoutUi
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun WorkoutList(workouts: List<WorkoutUi>, onWorkoutClick: (Long) -> Unit) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(workouts) { workout ->
            WorkoutItem(workout, onWorkoutClick)
        }
    }
}

@Composable
fun WorkoutItem(workout: WorkoutUi, onWorkoutClick: (Long) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { workout.id?.let { onWorkoutClick(it) } },
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = workout.title, style = MaterialTheme.typography.headlineSmall)
            Text(text = "Date: ${formatDate(workout.dateMillis)}", style = MaterialTheme.typography.bodyMedium)
            Text(text = "Exercises: ${workout.exercises.size}", style = MaterialTheme.typography.bodyMedium)
        }
    }
}

fun formatDate(millis: Long): String {
    val formatter = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
    return formatter.format(Date(millis))
}

