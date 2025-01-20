package com.example.workoutlogger

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.workoutlogger.ui.WorkoutViewModel
import com.example.workoutlogger.ui.theme.WorkoutLoggerTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyWorkoutApp()
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    private fun MyWorkoutApp() {
        WorkoutLoggerTheme {

            // Get an instance of WorkoutViewModel via Hilt
            val workoutViewModel: WorkoutViewModel = viewModel()

            // Collect the workout list from the VM
            val workouts by workoutViewModel.workoutList.collectAsStateWithLifecycle()

            // Simple text field state for adding new workouts
            var newWorkoutTitle by remember { mutableStateOf("") }

            Scaffold(
                topBar = {
                    TopAppBar(title = { Text("My Workout App") })
                },
                floatingActionButton = {
                    FloatingActionButton(onClick = {
                        if (newWorkoutTitle.isNotBlank()) {
                            workoutViewModel.addNewWorkout(newWorkoutTitle)
                            newWorkoutTitle = ""
                        }
                    }) {
                        Text("+")
                    }
                }
            ) { innerPadding ->
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    // Text field to enter workout title
                    OutlinedTextField(
                        value = newWorkoutTitle,
                        onValueChange = { newWorkoutTitle = it },
                        label = { Text("New Workout Title") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 16.dp)
                    )

                    Text("Workouts", style = MaterialTheme.typography.headlineSmall)

                    Spacer(modifier = Modifier.height(8.dp))

                    LazyColumn(modifier = Modifier.fillMaxWidth()) {
                        items(workouts) { workout ->
                            Text(
                                text = "- ${workout.title} (id=${workout.id})",
                                style = MaterialTheme.typography.bodyLarge,
                                modifier = Modifier.padding(vertical = 4.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}
