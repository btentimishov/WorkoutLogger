package com.baktyiar.ui_components.components.workout

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.baktyiar.ui_components.components.exercise.ExerciseItem
import com.baktyiar.ui_components.model.Exercise
import com.baktyiar.ui_components.model.ExerciseSet
import java.util.UUID

/*
@Composable
fun WorkoutScreen(
    exercises: List<Exercise>,
    onExercisesChange: (List<Exercise>) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
            .padding(16.dp)
    ) {
        // Header
        Text(
            text = "Workout",
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.onSurface
        )

        Spacer(modifier = Modifier.height(16.dp))

        // List of Exercises
        LazyColumn(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(exercises) { exercise ->
                ExerciseItem(
                    exerciseName = exercise.name,
                    sets = exercise.sets,
                    onAddSet = {
                        onExercisesChange(
                            exercises.map {
                                if (it.id == exercise.id) {
                                    it.copy(sets = it.sets + ExerciseSet(50, 10, false))
                                } else it
                            }
                        )
                    },
                    onDeleteExercise = {
                        onExercisesChange(exercises.filter { it.id != exercise.id })
                    },
                    onSetChange = { setIndex, updatedSet ->
                        onExercisesChange(
                            exercises.map {
                                if (it.id == exercise.id) {
                                    it.copy(sets = it.sets.toMutableList().apply { this[setIndex] = updatedSet })
                                } else it
                            }
                        )
                    },
                    onSetDelete = { setIndex ->
                        onExercisesChange(
                            exercises.map {
                                if (it.id == exercise.id) {
                                    it.copy(sets = it.sets.toMutableList().apply { removeAt(setIndex) })
                                } else it
                            }
                        )
                    }
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Add Exercise Button
        Button(
            onClick = {
                onExercisesChange(
                    exercises + Exercise(
                        id = UUID.randomUUID().toString(),
                        name = "New Exercise",
                        sets = listOf(ExerciseSet(50, 10, false))
                    )
                )
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Add Exercise")
        }
    }
}

@Preview
@Composable
fun PreviewWorkoutScreen() {
    var exercises by remember { mutableStateOf(getFakeExercises()) }

    MaterialTheme {
        WorkoutScreen(
            exercises = exercises,
            onExercisesChange = { exercises = it }
        )
    }
}

fun getFakeExercises(): List<Exercise> {
    return listOf(
        Exercise(
            id = UUID.randomUUID().toString(),
            name = "Bench Press",
            sets = listOf(
                ExerciseSet(weight = 60, reps = 10, isComplete = false),
                ExerciseSet(weight = 65, reps = 8, isComplete = false)
            )
        ),
        Exercise(
            id = UUID.randomUUID().toString(),
            name = "Squat",
            sets = listOf(
                ExerciseSet(weight = 80, reps = 10, isComplete = false),
                ExerciseSet(weight = 85, reps = 8, isComplete = false)
            )
        )
    )
}
*/
