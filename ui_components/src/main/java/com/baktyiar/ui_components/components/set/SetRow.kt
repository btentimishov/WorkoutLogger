package com.baktyiar.ui_components.components.set

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.baktyiar.ui_components.model.ExerciseSetUi

@Composable
fun ExerciseSetRow(
    exerciseSet: ExerciseSetUi,
    onWeightChange: (Float) -> Unit,
    onRepsChange: (Int) -> Unit,
    onStatusChange: () -> Unit,
    onSetDelete: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surface, shape = RoundedCornerShape(8.dp))
            .padding(horizontal = 2.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        SetIndicator(setNumber = exerciseSet.order)

        Spacer(modifier = Modifier.width(4.dp))

        WeightInput(weight = exerciseSet.weight, onWeightChange = onWeightChange)

        Spacer(modifier = Modifier.width(4.dp))

        RepsInput(reps = exerciseSet.reps, onRepsChange = onRepsChange)

        Spacer(modifier = Modifier.width(4.dp))

        StatusButton(isComplete = exerciseSet.isComplete, onClick = onStatusChange)

        IconButton(onClick = onSetDelete) {
            Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete Set")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewExerciseSetRow() {
    ExerciseSetRow(
        exerciseSet = ExerciseSetUi(
            order = 1,
            weight = 50f,
            reps = 10,
            isComplete = false,
            id = 1
        ),
        onWeightChange = { /* Handle weight change */ },
        onRepsChange = { /* Handle reps change */ },
        onStatusChange = { /* Handle status toggle */ },
        onSetDelete = { /* Handle set deletion */ }
    )
}
