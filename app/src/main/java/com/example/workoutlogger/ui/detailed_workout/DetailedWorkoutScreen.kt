package com.example.workoutlogger.ui.detailed_workout

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.baktyiar.ui_components.components.workout.WorkoutScreen
import com.example.workoutlogger.ui.toDomain
import com.example.workoutlogger.ui.toUiModel
import androidx.compose.material3.TextFieldDefaults.colors


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailedWorkoutScreen(
    workoutId: Long,
    navController: NavHostController,
    viewModel: DetailedWorkoutViewModel = hiltViewModel()
) {

    val uiState = viewModel.workoutsUiState.collectAsState()
    val keyboardController = LocalSoftwareKeyboardController.current

    LaunchedEffect(key1 = workoutId) {
        if (workoutId == -1L) {
            viewModel.createEmptyWorkout()
        } else {
            viewModel.getWorkout(workoutId)
        }
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
                                KeyboardActions(
                                    onDone = { keyboardController?.hide() })
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
                },
                actions = {
                    IconButton(onClick = {
                        viewModel.deleteWorkout(workoutId)
                        navController.popBackStack()
                    }) {
                        Icon(Icons.Default.Delete, contentDescription = "Delete workout")
                    }
                }
            )
        }
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            when (val state = uiState.value) {
                is WorkoutUiState.Loading -> CircularProgressIndicator()

                is WorkoutUiState.Success -> WorkoutScreen(
                    state.workout.toUiModel(),
                    onWorkoutChange = { workout ->
                        viewModel.updateWorkout(workout.toDomain())
                    },
                    onSaveWorkoutButtonClick = {
                        navController.popBackStack()
                        viewModel.saveWorkout()
                    },
                    onExerciseChange = { exercises ->
                        viewModel.onExerciseChange(exercises.toDomain())
                    },
                    onDeleteExercise = { exercise ->
                        viewModel.deleteExercise(exercise.toDomain())
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

                is WorkoutUiState.Error -> TODO()
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
            ),
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                errorIndicatorColor = Color.Transparent,
                // If you also want no container background, set these to Transparent:
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                disabledContainerColor = Color.Transparent,
                errorContainerColor = Color.Transparent
            )
        )
    } else {
        Text(
            text = title.ifEmpty { "Enter Workout Title" },
            color = MaterialTheme.colorScheme.onSurface,
            style = LocalTextStyle.current.copy(color = MaterialTheme.colorScheme.onSurface),
            modifier = Modifier
                .fillMaxWidth()
                .clickable { isEditing = true }
        )
    }
}
