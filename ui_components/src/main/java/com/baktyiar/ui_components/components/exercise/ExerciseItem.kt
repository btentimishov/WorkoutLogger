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
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.baktyiar.ui_components.components.set.ExerciseSetRow
import com.baktyiar.ui_components.model.ExerciseSetUi
import com.baktyiar.ui_components.model.ExerciseUi

@Composable
fun ExerciseItem(
    exercise: ExerciseUi,
    onAddSet: () -> Unit,
    onSetChange: (ExerciseSetUi) -> Unit,
    onSetDelete: (Long?) -> Unit,
    onExerciseChange: (ExerciseUi) -> Unit,
    onDeleteExercise: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .background(MaterialTheme.colorScheme.surface, shape = RoundedCornerShape(8.dp))
            .padding(16.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {

            TextField(
                value = exercise.name,
                onValueChange = { onExerciseChange(exercise.copy(name = it)) },
                placeholder = {
                    Text(
                        text = "Type exercise name",
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                },
                modifier = Modifier.weight(1f),
                textStyle = MaterialTheme.typography.titleLarge,
                colors = TextFieldDefaults.colors(
                    focusedTextColor = MaterialTheme.colorScheme.onSurface,
                    unfocusedTextColor = MaterialTheme.colorScheme.onSurface,
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    cursorColor = MaterialTheme.colorScheme.primary,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                )
            )

            IconButton(onClick = onDeleteExercise) {
                Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete Exercise")
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        exercise.sets.forEachIndexed { _, set ->
            ExerciseSetRow(
                exerciseSet = set,
                onWeightChange = { newWeight ->
                    onSetChange(set.copy(weight = newWeight))
                },
                onRepsChange = { newReps ->
                    onSetChange(set.copy(reps = newReps))
                },
                onStatusChange = {
                    onSetChange(set.copy(isComplete = !set.isComplete))
                },
                onSetDelete = {
                    onSetDelete(set.id)
                }
            )
            Spacer(modifier = Modifier.height(4.dp))
        }

        OutlinedButton(
            onClick = onAddSet,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Add Set")
        }
    }
}