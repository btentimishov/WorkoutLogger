package com.baktyiar.ui_components.components.exercise

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.baktyiar.ui_components.components.set.ExerciseSetRow
import com.baktyiar.ui_components.model.ExerciseSet

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExerciseItem(
    exerciseName: String,
    sets: List<ExerciseSet>,
    onAddSet: () -> Unit,
    onDeleteExercise: () -> Unit,
    onSetChange: (Int, ExerciseSet) -> Unit,
    onSetDelete: (Int) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .background(MaterialTheme.colorScheme.surface, shape = RoundedCornerShape(8.dp))
            .padding(16.dp)
    ) {
        // Exercise Name Row
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = exerciseName,
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onSurface
            )
            IconButton(onClick = onDeleteExercise) {
                Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete Exercise")
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        sets.forEachIndexed { index, set ->
            ExerciseSetRow(
                setNumber = index + 1,
                weight = set.weight,
                reps = set.reps,
                isComplete = set.isComplete,
                onWeightChange = { newWeight ->
                    onSetChange(index, set.copy(weight = newWeight))
                },
                onRepsChange = { newReps ->
                    onSetChange(index, set.copy(reps = newReps))
                },
                onStatusChange = {
                    onSetChange(index, set.copy(isComplete = !set.isComplete))
                }
            )
            Spacer(modifier = Modifier.height(4.dp))
        }

        // Add Set Button
        OutlinedButton(
            onClick = onAddSet,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Add Set")
        }
    }
}

@Preview
@Composable
fun PreviewExerciseItem() {
    var sets by remember { mutableStateOf(listOf(ExerciseSet(50, 10, false))) }

    MaterialTheme(colorScheme = lightColorScheme()) {
        ExerciseItem(
            exerciseName = "Bench Press",
            sets = sets,
            onAddSet = {
                sets = sets + ExerciseSet(50, 10, false) // Add a new set with default values
            },
            onDeleteExercise = {
                // Handle deleting the entire exercise
            },
            onSetChange = { index, updatedSet ->
                sets = sets.toMutableList().apply { this[index] = updatedSet }
            },
            onSetDelete = { index ->
                sets = sets.toMutableList().apply { removeAt(index) }
            }
        )
    }
}
