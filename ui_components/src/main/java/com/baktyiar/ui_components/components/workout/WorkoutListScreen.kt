package com.baktyiar.ui_components.components.workout

import android.hardware.lights.Light
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
import com.baktyiar.ui_components.theme.LightColors
import androidx.compose.ui.tooling.preview.Preview
import com.baktyiar.ui_components.model.ExerciseUi

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

@Preview
@Composable
fun WorkoutListPreviewEmpty() {
    MaterialTheme(colorScheme = LightColors) {
        WorkoutList(
            workouts = emptyList(),
            onDeleteWorkout = {},
            onWorkoutClick = {}
        )
    }
}

@Preview
@Composable
fun WorkoutListPreviewNotEmpty() {
    MaterialTheme(colorScheme = LightColors) {
        WorkoutList(
            workouts = listOf(
                WorkoutUi(
                    id = 1,
                    title = "Morning Workout",
                    dateMillis = 1680602400000, // Just a random timestamp
                    exercises = mutableListOf(
                        ExerciseUi(id = 11, name = "Push Ups", order = 1),
                        ExerciseUi(id = 12, name = "Sit Ups", order = 2),
                    )
                ),
                WorkoutUi(
                    id = 2,
                    title = "Evening Workout",
                    dateMillis = 1680688800000,
                    exercises = mutableListOf(
                        ExerciseUi(id = 21, name = "Squats", order = 1),
                        ExerciseUi(id = 22, name = "Lunges", order = 2),
                    )
                )
            ),
            onDeleteWorkout = {},
            onWorkoutClick = {}
        )
    }
}
