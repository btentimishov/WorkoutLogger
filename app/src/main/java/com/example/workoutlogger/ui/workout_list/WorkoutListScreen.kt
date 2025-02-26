package com.example.workoutlogger.ui.workout_list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.baktyiar.ui_components.components.PrimaryFAB
import com.baktyiar.ui_components.components.WorkoutTopAppBar
import com.baktyiar.ui_components.components.workout.WorkoutList
import com.example.workoutlogger.R
import com.example.workoutlogger.Screen

@Composable
fun WorkoutListScreen(
    navController: NavHostController,
    viewModel: WorkoutListViewModel = hiltViewModel()
) {
    val uiState by viewModel.workoutUiState.collectAsState()

    Scaffold(
        topBar = {
            CustomTopAppBar()
        },
        floatingActionButton = {
            PrimaryFAB(
                text = "Add",
                onClick = {
                    navController.navigate(Screen.CreateWorkout.route)
                }
            )
        }
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            when (val state = uiState) {
                is WorkoutListUiState.Loading -> LoadingState()
                is WorkoutListUiState.Success -> {
                    WorkoutList(state.workouts, onDeleteWorkout = {
                        viewModel.deleteWorkout(it.id)
                    }) { workoutId ->
                        navController.navigate(Screen.WorkoutDetail.createRoute(workoutId))
                    }
                }

                is WorkoutListUiState.Error -> {
                    ErrorState(message = state.message, onRetry = {})
                }
            }
        }
    }
}

@Composable
private fun CustomTopAppBar() {
    WorkoutTopAppBar(
        title = { Text(stringResource(R.string.workout_list_title)) },
    )
}

@Composable
fun ErrorState(message: String, onRetry: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = message, color = Color.Red, style = MaterialTheme.typography.bodyLarge)
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = onRetry) {
            Text(text = "Retry")
        }
    }
}

@Composable
fun LoadingState() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        CircularProgressIndicator()
    }
}
