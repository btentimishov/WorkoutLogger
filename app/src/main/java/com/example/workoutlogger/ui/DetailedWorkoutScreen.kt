package com.example.workoutlogger.ui

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import com.baktyiar.ui_components.components.workout.WorkoutScreen

@Composable
fun DetailedWorkoutScreen(viewModel: DetailedWorkoutViewModel = hiltViewModel()) {

    val uiState = viewModel.workoutsUiState.collectAsState()

    LaunchedEffect(key1 = uiState) {
        viewModel.getWorkout(1)
    }
    when (val state = uiState.value) {
        is WorkoutUiState.Loading -> {
            CircularProgressIndicator()
        }

        is WorkoutUiState.Success -> {
            WorkoutScreen(
                state.workout.toUiModel(),
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