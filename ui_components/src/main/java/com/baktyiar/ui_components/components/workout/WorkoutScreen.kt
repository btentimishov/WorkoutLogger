package com.baktyiar.ui_components.components.workout

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.baktyiar.ui_components.components.PrimaryButton
import com.baktyiar.ui_components.components.SecondaryButton
import com.baktyiar.ui_components.components.exercise.ExerciseItem
import com.baktyiar.ui_components.model.ExerciseSetUi
import com.baktyiar.ui_components.model.ExerciseUi
import com.baktyiar.ui_components.model.WorkoutUi
import com.baktyiar.ui_components.theme.LightColors

@Composable
fun WorkoutScreen(
    workout: WorkoutUi,
    onSaveWorkoutClick: () -> Unit,
    onAddExerciseClick: () -> Unit,
    onDeleteExercise: (ExerciseUi) -> Unit,
    onExerciseChange: (ExerciseUi) -> Unit,
    onAddSet: (ExerciseUi) -> Unit,
    onSetDelete: (ExerciseSetUi, ExerciseUi) -> Unit,
    onSetChange: (ExerciseUi, ExerciseSetUi) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
            .imePadding()
    ) {

        LazyColumn(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(workout.exercises) { exercise ->
                ExerciseItem(
                    exercise = exercise,
                    onAddSet = { onAddSet(exercise) },
                    onSetChange = { set ->
                        onSetChange(exercise, set)
                    },
                    onSetDelete = { set ->
                        onSetDelete(set, exercise)
                    },
                    onDeleteExercise = { onDeleteExercise(exercise) },
                    onExerciseChange = onExerciseChange
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))
        SecondaryButton(
            text = "Add Exercise",
            onClick = onAddExerciseClick,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        PrimaryButton(
            text = "Save Workout",
            onClick = onSaveWorkoutClick,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Preview(backgroundColor = 0xFFFFFFFF)
@Composable
fun WorkoutScreenPreview() {
    MaterialTheme(colorScheme = LightColors) {
        WorkoutScreen(
            workout = WorkoutUi(
                id = 1,
                title = "Preview Workout",
                exercises = mutableListOf(
                    ExerciseUi(
                        id = 10,
                        name = "Bench Press",
                        order = 1,
                        sets = mutableListOf(
                            ExerciseSetUi(id = 100, weight = 50f, reps = 10, order = 1, isComplete = false),
                            ExerciseSetUi(id = 101, weight = 50f, reps = 8, order = 2, isComplete = false),
                        )
                    ),
                    ExerciseUi(
                        id = 20,
                        name = "Squat",
                        order = 2,
                        sets = mutableListOf(
                            ExerciseSetUi(id = 200, weight = 60f, reps = 10, order = 1, isComplete = true),
                            ExerciseSetUi(id = 201, weight = 60f, reps = 8, order = 2, isComplete = false),
                        )
                    )
                )
            ),
            onSaveWorkoutClick = {},
            onAddExerciseClick = {},
            onDeleteExercise = {},
            onExerciseChange = {},
            onAddSet = {},
            onSetDelete = { _, _ -> },
            onSetChange = { _, _ -> }
        )
    }
}