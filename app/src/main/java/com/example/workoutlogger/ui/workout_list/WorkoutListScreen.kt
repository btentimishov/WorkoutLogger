package com.example.workoutlogger.ui.workout_list

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.baktyiar.domain.model.Workout
import com.baktyiar.ui_components.components.workout.WorkoutList
import com.example.workoutlogger.Screen
import com.example.workoutlogger.ui.toUiModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WorkoutListScreen(navController: NavHostController, viewModel: WorkoutListViewModel = hiltViewModel()) {
    val uiState by viewModel.workoutUiState.collectAsState()



    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Workout List") })
        }
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            when (val state = uiState) {
                is WorkoutListUiState.Loading -> {
                    Log.d("WorkoutListScreen", "Loading state")
                    LoadingState()
                }

                is WorkoutListUiState.Success -> {
                    val workouts = state.workouts
                    Log.d("WorkoutListScreen", "Workouts: $workouts")
                    if (workouts.isEmpty()) {
                        EmptyState()
                    } else {
                        WorkoutList(workouts.map { it.toUiModel() }) { workoutId ->
//                            navController.navigate("workout_detail/$it")
                            navController.navigate(Screen.WorkoutDetail.createRoute(workoutId))
                        }
                    }
                }

                is WorkoutListUiState.Error -> {
                    Log.d("WorkoutListScreen", "Error state")
                    val errorMessage = state.message
                    ErrorState(message = errorMessage, onRetry = {})
                }
            }
        }
    }
}

@Composable
fun ErrorState(message: String, onRetry: () -> Unit) {
    /*    Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = message, color = Color.Red, style = MaterialTheme.typography.body1)
            Spacer(modifier = Modifier.height(8.dp))
            Button(onClick = onRetry) {
                Text(text = "Retry")
            }
        }*/
}

@Composable
fun EmptyState() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(
            text = "No workouts found",
            style = MaterialTheme.typography.bodyLarge,
            color = Color.Gray
        )
    }
}

@Composable
fun LoadingState() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        CircularProgressIndicator()
    }
}
