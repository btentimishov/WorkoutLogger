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
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.baktyiar.ui_components.components.SecondaryButton
import com.baktyiar.ui_components.components.set.ExerciseSetRow
import com.baktyiar.ui_components.components.set.OrderIndicator
import com.baktyiar.ui_components.model.ExerciseSetUi
import com.baktyiar.ui_components.model.ExerciseUi
import com.baktyiar.ui_components.theme.Shapes

@Composable
fun ExerciseItem(
    exercise: ExerciseUi,
    onAddSet: () -> Unit,
    onSetChange: (ExerciseSetUi) -> Unit,
    onSetDelete: (ExerciseSetUi) -> Unit,
    onExerciseChange: (ExerciseUi) -> Unit,
    onDeleteExercise: () -> Unit
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .background(MaterialTheme.colorScheme.surface, shape = Shapes.small)
            .padding(16.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            OrderIndicator(
                exercise.order,
                modifier = Modifier.background(
                    color = MaterialTheme.colorScheme.primary,
                    shape = Shapes.small
                )
            )
            TextField(
                value = exercise.name,
                onValueChange = { onExerciseChange(exercise.copy(name = it)) },
                placeholder = {
                    Text(
                        text = "Type exercise name",
                        style = MaterialTheme.typography.titleLarge,
                        color = Color.Gray
                    )
                },
                modifier = Modifier.weight(1f),
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(
                    onDone = {
                        keyboardController?.hide()
                    }
                ),
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
                    onSetDelete(set)
                },
            )
            Spacer(modifier = Modifier.height(4.dp))
        }

        SecondaryButton(
            "Add set",
            onClick = onAddSet,
            modifier = Modifier.fillMaxWidth()
        )
    }
}