package com.baktyiar.ui_components.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.baktyiar.ui_components.theme.WorkoutLoggerTheme

@Composable
fun PrimaryButton(
    text: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    val colorScheme = MaterialTheme.colorScheme
    Button(
        onClick = onClick,
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(
            containerColor = colorScheme.primary,
            contentColor = colorScheme.onPrimary
        )
    ) {
        Text(text = text)
    }
}


@Composable
fun SecondaryButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val colorScheme = MaterialTheme.colorScheme
    OutlinedButton(
        onClick = onClick,
        modifier = modifier
    ) {
        Text(text = text, color = colorScheme.primary)
    }
}

@Composable
fun PrimaryFAB(
    text: String? = null,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    val colorScheme = MaterialTheme.colorScheme
    FloatingActionButton(
        onClick = onClick,
        containerColor = colorScheme.primary,
        contentColor = colorScheme.onPrimary,
        modifier = modifier,
        elevation = FloatingActionButtonDefaults.elevation(
            defaultElevation = 4.dp
        )
    ) {
        if (text != null) {
            Text(text)

        } else {
            Icon(
                Icons.Default.Add,
                contentDescription = "Add")
        }
    }
}

//@Preview(showBackground = true, name = "PrimaryFAB Preview")
//@Composable
//fun PrimaryFABPreview() {
//    WorkoutLoggerTheme {
//        PrimaryFAB(onClick = {}, text = "Add")
//    }
//}
//
//
//@Preview(showBackground = true, name = "SecondaryButton Preview")
//@Composable
//fun SecondaryButtonPreview() {
//    WorkoutLoggerTheme {
//        SecondaryButton(text = "Cancel", onClick = {})
//    }
//}
//
//@Preview(showBackground = true)
//@Composable
//fun PrimaryButtonPreview() {
//    WorkoutLoggerTheme {
//        PrimaryButton(text = "Start Workout", onClick = {})
//    }
//}
//
