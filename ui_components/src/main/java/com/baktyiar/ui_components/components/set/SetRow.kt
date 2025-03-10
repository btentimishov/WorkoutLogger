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
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.baktyiar.ui_components.model.ExerciseSetUi
import com.baktyiar.ui_components.theme.DarkColors
import com.baktyiar.ui_components.theme.Shapes

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
            .background(MaterialTheme.colorScheme.surface, shape = Shapes.small)
            .padding(horizontal = 2.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        OrderIndicator(
            setNumber = exerciseSet.order,
            modifier = Modifier.background(
                color = MaterialTheme.colorScheme.secondary,
                shape = Shapes.medium
            ))

        Spacer(modifier = Modifier.width(1.dp))

        WeightInput(weight = exerciseSet.weight, onWeightChange = onWeightChange)

        Spacer(modifier = Modifier.width(1.dp))

        RepsInput(reps = exerciseSet.reps, onRepsChange = onRepsChange)

        Spacer(modifier = Modifier.width(1.dp))

        StatusButton(isComplete = exerciseSet.isComplete, onClick = onStatusChange)

        IconButton(onClick = onSetDelete) {
            Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete Set")
        }
    }
}


@Preview
@Composable
fun ExerciseSetRowPreviewIncomplete() {
    MaterialTheme(colorScheme = com.baktyiar.ui_components.theme.LightColors) {
        ExerciseSetRow(
            exerciseSet = ExerciseSetUi(
                id = 1,
                weight = 15f,
                reps = 8,
                order = 1,
                isComplete = false
            ),
            onWeightChange = {},
            onRepsChange = {},
            onStatusChange = {},
            onSetDelete = {}
        )
    }
}
@Preview
@Composable
fun ExerciseSetRowPreviewComplete() {
    MaterialTheme(colorScheme = com.baktyiar.ui_components.theme.LightColors) {
        ExerciseSetRow(
            exerciseSet = ExerciseSetUi(
                id = 1,
                weight = 15f,
                reps = 8,
                order = 1,
                isComplete = true
            ),
            onWeightChange = {},
            onRepsChange = {},
            onStatusChange = {},
            onSetDelete = {}
        )
    }
}

@Preview
@Composable
fun ExerciseSetRowDarkPreviewComplete() {
    MaterialTheme(colorScheme = DarkColors) {
        ExerciseSetRow(
            exerciseSet = ExerciseSetUi(
                id = 2,
                weight = 20f,
                reps = 10,
                order = 2,
                isComplete = true
            ),
            onWeightChange = {},
            onRepsChange = {},
            onStatusChange = {},
            onSetDelete = {}
        )
    }
}
@Preview
@Composable
fun ExerciseSetRowDarkPreviewInComplete() {
    MaterialTheme(colorScheme = DarkColors) {
        ExerciseSetRow(
            exerciseSet = ExerciseSetUi(
                id = 2,
                weight = 20f,
                reps = 10,
                order = 2,
                isComplete = false
            ),
            onWeightChange = {},
            onRepsChange = {},
            onStatusChange = {},
            onSetDelete = {}
        )
    }
}