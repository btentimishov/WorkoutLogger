package com.baktyiar.ui_components.components.workout

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.baktyiar.ui_components.components.exercise.ExerciseItem
import com.baktyiar.ui_components.model.ExerciseUi
import com.baktyiar.ui_components.model.ExerciseSetUi
import com.baktyiar.ui_components.model.WorkoutUi

//TODO replace IDs of sets to SET objects

@Composable
fun WorkoutScreen(
    workout: WorkoutUi,
    onWorkoutChange: (WorkoutUi) -> Unit,
    onSaveWorkoutClick: () -> Unit,
    onAddExerciseClick: () -> Unit,
    onDeleteExercise: (ExerciseUi) -> Unit,
    onExerciseChange: (ExerciseUi) -> Unit,
    onAddSet: (Long?) -> Unit,
    onSetDelete: (Long?, Long?) -> Unit,
    onSetChange: (Long?, ExerciseSetUi) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {

        LazyColumn(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(workout.exercises) { exercise ->
                ExerciseItem(
                    exercise = exercise,
                    onAddSet = { onAddSet(exercise.id) },
                    onSetChange = { set ->
                        onSetChange(exercise.id, set)
                    },
                    onSetDelete = { id ->
                        onSetDelete(id, exercise.id)
                    },
                    onDeleteExercise = { onDeleteExercise(exercise) },
                    onExerciseChange = onExerciseChange
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = onAddExerciseClick,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Add Exercise")
        }
        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = onSaveWorkoutClick,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Save workout")
        }
    }
}
