package com.baktyiar.ui_components.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
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
fun ExerciseCard(
    exerciseName: String,
    hasWeight: Boolean,
    hasDistance: Boolean,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(text = exerciseName, style = MaterialTheme.typography.titleMedium)
            if (hasWeight) {
                Text(text = "Weight-based exercise", style = MaterialTheme.typography.bodyMedium)
            }
            if (hasDistance) {
                Text(text = "Distance-based exercise", style = MaterialTheme.typography.bodyMedium)
            }
        }
    }
}

@Preview(showBackground = true, name = "ExerciseCard Preview - Weight")
@Composable
fun ExerciseCardWeightPreview() {
    WorkoutLoggerTheme {
        ExerciseCard(exerciseName = "Bench Press", hasWeight = true, hasDistance = false)
    }
}

@Preview(showBackground = true, name = "ExerciseCard Preview - Distance")
@Composable
fun ExerciseCardDistancePreview() {
    WorkoutLoggerTheme {
        ExerciseCard(exerciseName = "Running", hasWeight = false, hasDistance = true)
    }
}

