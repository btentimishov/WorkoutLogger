package com.baktyiar.ui_components.components.workout

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.baktyiar.ui_components.formatDate
import com.baktyiar.ui_components.model.WorkoutUi

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun WorkoutList(
    workouts: List<WorkoutUi>,
    onDeleteWorkout: (WorkoutUi) -> Unit,
    onWorkoutClick: (Long) -> Unit
) {
    if (workouts.isEmpty()) {
        EmptyState()
    } else {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(workouts) { workout ->
                SwipeBox(
                    onDelete = {
                        Log.d("SwipeBox", "onDelete: $workout")
                        onDeleteWorkout(workout)
                    },
                    modifier = Modifier.animateItem(fadeInSpec = null, fadeOutSpec = null)
                ) {
                    WorkoutItem(workout, onWorkoutClick)
                }
            }
        }
    }
}

@Composable
fun WorkoutItem(workout: WorkoutUi, onWorkoutClick: (Long) -> Unit) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .clickable { workout.id?.let { onWorkoutClick(it) } },
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = workout.title, style = MaterialTheme.typography.headlineSmall)
            Text(
                text = "Date: ${formatDate(workout.dateMillis)}",
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = "Exercises: ${workout.exercises.size}",
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

@Composable
fun EmptyState() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(
            text = "No workouts found",
            style = MaterialTheme.typography.bodyLarge,
            color = Color.Gray
        )
    }
}
