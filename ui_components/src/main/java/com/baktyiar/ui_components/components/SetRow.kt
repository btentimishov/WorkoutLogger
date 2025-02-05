package com.baktyiar.ui_components.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.baktyiar.ui_components.theme.WorkoutLoggerTheme

@Composable
fun SetRow(
    setNumber: Int,
    weight: Float?,
    reps: Int?,
    distance: Float?
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(text = "Set $setNumber:")
        weight?.let {
            Text(text = "${weight}kg")
        }
        reps?.let {
            Text(text = "x $reps")
        }
        distance?.let {
            Text(text = "${distance} km")
        }
    }
}

@Preview(showBackground = true, name = "SetRow Weight + Reps")
@Composable
fun SetRowPreviewWeightReps() {
    WorkoutLoggerTheme {
        SetRow(setNumber = 1, weight = 50f, reps = 10, distance = null)
    }
}

@Preview(showBackground = true, name = "SetRow Distance")
@Composable
fun SetRowPreviewDistance() {
    WorkoutLoggerTheme {
        SetRow(setNumber = 1, weight = null, reps = null, distance = 5f)
    }
}

