package com.baktyiar.ui_components.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ExerciseSetRow(
    setNumber: Int,
    weight: Int,
    reps: Int,
    isComplete: Boolean,
    onWeightChange: (Int) -> Unit,
    onRepsChange: (Int) -> Unit,
    onStatusChange: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .background(MaterialTheme.colorScheme.surface, shape = RoundedCornerShape(8.dp))
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        SetIndicator(setNumber = setNumber)

        Spacer(modifier = Modifier.width(8.dp))

        WeightInput(weight = weight, onWeightChange = onWeightChange)

        Spacer(modifier = Modifier.width(8.dp))

        RepsInput(reps = reps, onRepsChange = onRepsChange)

        Spacer(modifier = Modifier.width(8.dp))

        StatusButton(isComplete = isComplete, onClick = onStatusChange)
    }
}


@Preview(showBackground = true, name = "SetRow Weight + Reps")
@Composable
fun PreviewExerciseSetRow() {

    MaterialTheme(colorScheme = lightColorScheme()) {
        ExerciseSetRow(
            setNumber = 1,
            weight = 50,
            reps = 10,
            isComplete = false,
            onWeightChange = {},
            onRepsChange = {},
            onStatusChange = {}
        )
    }
}


