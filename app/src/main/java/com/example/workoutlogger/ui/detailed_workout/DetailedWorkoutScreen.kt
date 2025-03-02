package com.example.workoutlogger.ui.detailed_workout

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults.colors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.baktyiar.ui_components.components.WorkoutTopAppBar
import com.baktyiar.ui_components.components.workout.WorkoutScreen


@Composable
fun DetailedWorkoutRoute(
    workoutId: Long,
    navController: NavHostController,
    viewModel: DetailedWorkoutViewModel = hiltViewModel()
) {
    DetailedWorkoutScreen(
        workoutId = workoutId,
        onBack = {
            navController.popBackStack()
        },
        onDelete = {
            viewModel.deleteWorkout(workoutId)
            navController.popBackStack()
        },
        onWorkoutLoaded = { id ->
            if (id == -1L) {
                viewModel.createEmptyWorkout()
            } else {
                viewModel.getWorkout(id)
            }
        },
        viewModel = viewModel
    )
}


@Composable
fun DetailedWorkoutScreen(
    workoutId: Long,
    onBack: () -> Unit,
    onDelete: () -> Unit,
    onWorkoutLoaded: (Long) -> Unit,
    viewModel: DetailedWorkoutViewModel = hiltViewModel()
) {
    val uiState = viewModel.uiState.collectAsState()

    LaunchedEffect(workoutId) {
        onWorkoutLoaded(workoutId)
    }

    Scaffold(
        topBar = {
            WorkoutTopAppBar(
                title = {
                    when (val state = uiState.value) {
                        is WorkoutUiState.Success -> {
                            EditableWorkoutTitle(
                                title = state.workout.title,
                                onTitleChange = { newTitle ->
                                    viewModel.updateWorkout(state.workout.copy(title = newTitle))
                                }
                            )
                        }

                        else -> Text("Workout Detail")
                    }
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
                actions = {
                    IconButton(onClick = onDelete) {
                        Icon(
                            Icons.Default.Delete,
                            contentDescription = "Delete workout",
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            when (val state = uiState.value) {
                is WorkoutUiState.Loading -> {
                    CircularProgressIndicator()
                }

                is WorkoutUiState.Success -> {
                    WorkoutScreen(
                        workout = state.workout,
                        onSaveWorkoutClick = {
                            viewModel.saveWorkout()
                            onBack()
                        },
                        onExerciseChange = { exercises ->
                            viewModel.onExerciseChange(exercises)
                        },
                        onDeleteExercise = { exercise ->
                            viewModel.deleteExercise(exercise)
                        },
                        onAddSet = { exercise ->
                            viewModel.addEmptySet(exercise)
                        },
                        onSetChange = { exercise, set ->
                            viewModel.onSetChange(exercise, set)
                        },
                        onSetDelete = { set, exercise ->
                            viewModel.deleteSet(exercise, set)
                        },
                        onAddExerciseClick = {
                            viewModel.addEmptyExercise()
                        }
                    )
                }

                is WorkoutUiState.Error -> {
                    Text("Something went wrong. Please try again.")
                }
            }
        }
    }
}

@Composable
fun EditableWorkoutTitle(
    title: String,
    onTitleChange: (String) -> Unit
) {
    var textState by rememberSaveable(stateSaver = TextFieldValue.Saver) {
        mutableStateOf(TextFieldValue(title))
    }

    val keyboardController = LocalSoftwareKeyboardController.current

    OutlinedTextField(
        value = textState,
        onValueChange = { newValue ->
            textState = newValue
            onTitleChange(newValue.text)
        },
        placeholder = {
            Text(
                text = "Enter Workout Title",
                style = MaterialTheme.typography.titleLarge,
                color = Color.Gray
            )
        },
        singleLine = true,
        textStyle = MaterialTheme.typography.titleLarge.copy(
            color = MaterialTheme.colorScheme.onSurface
        ),
        modifier = Modifier
            .fillMaxWidth(),
        keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
        keyboardActions = KeyboardActions(
            onDone = {
                keyboardController?.hide()
            }
        ),
        colors = colors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            errorIndicatorColor = Color.Transparent,
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent,
            disabledContainerColor = Color.Transparent,
            errorContainerColor = Color.Transparent
        )
    )
}

