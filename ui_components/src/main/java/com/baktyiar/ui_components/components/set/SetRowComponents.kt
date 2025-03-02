package com.baktyiar.ui_components.components.set

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.baktyiar.ui_components.theme.Shapes

@Composable
fun StatusButton(
    isComplete: Boolean,
    onClick: () -> Unit
) {
    val backgroundColor =
        if (isComplete) MaterialTheme.colorScheme.primary else Color.Gray
    val contentColor = MaterialTheme.colorScheme.onPrimary

    Box(
        modifier = Modifier
            .size(30.dp),
        contentAlignment = Alignment.Center,

        ) {
        IconButton(
            onClick = onClick
        ) {
            Icon(
                imageVector = Icons.Default.Check,
                contentDescription = if (isComplete) "Complete" else "Incomplete",
                tint = contentColor,
                modifier = Modifier
                    .background(backgroundColor, shape = Shapes.extraSmall)
            )
        }
    }
}

@Composable
fun OrderIndicator(setNumber: Int, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .size(30.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = setNumber.toString(),
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onPrimary
        )
    }
}

@Composable
fun WeightInput(weight: Float?, unit: String = "KG", onWeightChange: (Float) -> Unit) {
    val initialValue = if (weight == null || weight == 0.0f) "" else weight.toString()
    var textValue by remember { mutableStateOf(initialValue) }


    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .background(MaterialTheme.colorScheme.surface, shape = Shapes.small)
            .padding(vertical = 4.dp)
    ) {

        TextField(
            value = textValue,
            onValueChange = { newValue ->
                textValue = newValue
                newValue.toFloatOrNull()?.let(onWeightChange)
            },
            singleLine = true,
            modifier = Modifier.width(80.dp),
            textStyle = MaterialTheme.typography.bodyLarge,
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            visualTransformation = VisualTransformation.None,
            placeholder = {
                Text("00.00")
            },
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
        Text(
            text = unit,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}

@Composable
fun RepsInput(reps: Int, onRepsChange: (Int) -> Unit) {

    val initialValue = if (reps == 0) "" else reps.toString()

    var textValue by remember { mutableStateOf(initialValue) }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .background(MaterialTheme.colorScheme.surface, shape = Shapes.small)
            .padding(horizontal = 4.dp, vertical = 4.dp)
    ) {
        Text(
            text = "x",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurface
        )
        Spacer(modifier = Modifier.width(8.dp))
        TextField(
            value = textValue,
            onValueChange = { newValue ->
                textValue = newValue
                val newReps = newValue.toIntOrNull()
                newReps?.let(onRepsChange)
            },
            modifier = Modifier.width(60.dp),
            singleLine = true,
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            visualTransformation = VisualTransformation.None,
            placeholder = { Text("00") },
            textStyle = MaterialTheme.typography.bodyLarge,
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
    }
}


@Preview
@Composable
fun StatusButtonCompletePreview() {
    MaterialTheme(colorScheme = lightColorScheme()) {
        StatusButton(
            isComplete = true,
            onClick = {}
        )
    }
}

@Preview
@Composable
fun StatusButtonIncompletePreview() {
    MaterialTheme(colorScheme = lightColorScheme()) {
        StatusButton(
            isComplete = false,
            onClick = {}
        )
    }
}

@Preview
@Composable
fun OrderIndicatorPreview() {
    MaterialTheme(colorScheme = lightColorScheme()) {
        OrderIndicator(setNumber = 3)
    }
}

@Preview
@Composable
fun WeightInputPreview() {
    MaterialTheme(colorScheme = lightColorScheme()) {
        // Displaying in a Column just to keep it tidy in preview
        Column {
            WeightInput(weight = 0f, onWeightChange = {})
            WeightInput(weight = 15.5f, onWeightChange = {})
        }
    }
}

@Preview
@Composable
fun RepsInputPreview() {
    MaterialTheme(colorScheme = lightColorScheme()) {
        // Displaying in a Column just to keep it tidy in preview
        Column {
            RepsInput(reps = 0, onRepsChange = {})
            RepsInput(reps = 12, onRepsChange = {})
        }
    }
}