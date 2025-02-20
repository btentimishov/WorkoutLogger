package com.example.workoutlogger.ui.detailed_workout

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.baktyiar.ui_components.components.workout.WorkoutScreen
import com.example.workoutlogger.ui.toDomain
import com.example.workoutlogger.ui.toUiModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailedWorkoutScreen(
    workoutId: Long,
    navController: NavHostController,
    viewModel: DetailedWorkoutViewModel = hiltViewModel()
) {

    val uiState = viewModel.workoutsUiState.collectAsState()

    LaunchedEffect(key1 = uiState) {
        viewModel.getWorkout(workoutId)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {

                    if (uiState.value is WorkoutUiState.Success) {
                        val workout = (uiState.value as WorkoutUiState.Success).workout
                        EditableWorkoutTitle(
                            title = workout.title,
                            onTitleChange = { viewModel.updateWorkout(workout.copy(title = it)) },
                            onDoneEditing = {

                            }

                        )
                    } else {
                        Text("Workout Detail")
                    }
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues), contentAlignment = Alignment.Center) {
            when (val state = uiState.value) {
                is WorkoutUiState.Loading -> {
                    CircularProgressIndicator()
                }

                is WorkoutUiState.Success -> {
                    WorkoutScreen(
                        state.workout.toUiModel(),
                        onWorkoutChange = { workout ->
                            viewModel.updateWorkout(workout.toDomain())
                        },
                        onSaveWorkoutButtonClick = {
                            viewModel.saveWorkout()
                        },
                        onExerciseChange = { exercises ->
                            viewModel.onExerciseChange(exercises.toDomain())
                        },
                        onDeleteExercise = { id ->
                            viewModel.deleteExercise(id)
                        },
                        onAddSet = { id ->
                            viewModel.addEmptySet(id)
                        },
                        onSetChange = { exerciseId, exerciseSet ->
                            viewModel.onSetChange(exerciseId, exerciseSet.toDomain())
                        },
                        onSetDelete = { setId, exerciseId ->
                            viewModel.deleteSet(setId, exerciseId)
                        },
                        onAddExerciseButtonClick = {
                            viewModel.addEmptyExercise()
                        }
                    )
                }

                else -> {
                    Text(text = "Error")
                }
            }
        }
    }
}

@Composable
fun EditableWorkoutTitle(
    title: String,
    onTitleChange: (String) -> Unit,
    onDoneEditing: () -> Unit
) {
    var isEditing by remember { mutableStateOf(false) }
    var textState by remember { mutableStateOf(TextFieldValue(title)) }

    if (isEditing) {
        OutlinedTextField(
            value = textState,
            onValueChange = { newValue ->
                textState = newValue
                onTitleChange(newValue.text)
            },
            singleLine = true,
            textStyle = LocalTextStyle.current.copy(color = MaterialTheme.colorScheme.onSurface),
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(
                onDone = {
                    isEditing = false
                    onDoneEditing()
                }
            )
        )
    } else {
        Text(
            text = title.ifEmpty { "Enter Workout Title" },
            color = MaterialTheme.colorScheme.onSurface,
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier
                .fillMaxWidth()
                .clickable { isEditing = true }
        )
    }
}
