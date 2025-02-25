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
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.baktyiar.ui_components.components.exercise.ExerciseItem
import com.baktyiar.ui_components.model.ExerciseUi
import com.baktyiar.ui_components.model.ExerciseSetUi
import com.baktyiar.ui_components.model.WorkoutUi

@Composable
fun WorkoutScreen(
    workout: WorkoutUi,
    onWorkoutChange: (WorkoutUi) -> Unit,
    onSaveWorkoutButtonClick: () -> Unit,
    onAddExerciseButtonClick: () -> Unit,
    onDeleteExercise: (ExerciseUi) -> Unit,
    onExerciseChange: (ExerciseUi) -> Unit,
    onAddSet: (Long?) -> Unit,
    onSetDelete: (Long?, Long?) -> Unit,
    onSetChange: (Long?, ExerciseSetUi) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
            .padding(16.dp)
            .statusBarsPadding()
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
            onClick = onAddExerciseButtonClick,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Add Exercise")
        }
        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = onSaveWorkoutButtonClick,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Save workout")
        }
    }
}
