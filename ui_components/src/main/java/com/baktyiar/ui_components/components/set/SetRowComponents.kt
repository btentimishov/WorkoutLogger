package com.baktyiar.ui_components.components.set

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
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
import com.baktyiar.ui_components.theme.WorkoutLoggerTheme

@Composable
fun SetIndicator(setNumber: Int) {
    Box(
        modifier = Modifier
            .size(40.dp)
            .background(MaterialTheme.colorScheme.secondary, shape = RoundedCornerShape(8.dp)),
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
    var textValue by remember { mutableStateOf(weight.toString()) }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .background(MaterialTheme.colorScheme.surface, shape = RoundedCornerShape(8.dp))
            .padding(horizontal = 4.dp, vertical = 4.dp)
    ) {


        TextField(
            value = textValue,
            onValueChange = { newValue ->
                textValue = newValue
                val newWeight = newValue.toFloatOrNull()
                newWeight?.let(onWeightChange)
            },
            modifier = Modifier.width(60.dp),
            singleLine = true,
            textStyle = MaterialTheme.typography.bodyLarge,
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            visualTransformation = VisualTransformation.None,
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
    var textValue by remember { mutableStateOf(reps.toString()) }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .background(MaterialTheme.colorScheme.surface, shape = RoundedCornerShape(8.dp))
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

@Composable
fun StatusButton(
    isComplete: Boolean,
    onClick: () -> Unit
) {
    val backgroundColor =
        if (isComplete) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.error
    val contentColor = MaterialTheme.colorScheme.onPrimary

    Box(
        contentAlignment = Alignment.Center
    ) {
        IconButton(
            onClick = onClick
        ) {
            Icon(
                imageVector = if (isComplete) Icons.Default.Check else Icons.Default.Close,
                contentDescription = if (isComplete) "Complete" else "Incomplete",
                tint = contentColor,
                modifier = Modifier
                    .background(backgroundColor, shape = RoundedCornerShape(8.dp))
            )
        }
    }
}

@Preview
@Composable
fun PreviewStatusButton() {
    var isComplete by remember { mutableStateOf(false) }

    MaterialTheme(colorScheme = lightColorScheme()) {
        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            StatusButton(isComplete = isComplete) {
                isComplete = !isComplete
            }
            StatusButton(isComplete = !isComplete) {
                isComplete = !isComplete
            }
        }
    }
}