package com.example.workoutlogger

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.workoutlogger.ui.detailed_workout.DetailedWorkoutScreen
import com.example.workoutlogger.ui.workout_list.WorkoutListScreen

sealed class Screen(val route: String) {
    object WorkoutList : Screen("workout_list")
    object WorkoutDetail : Screen("workout_detail/{workoutId}") {
        fun createRoute(workoutId: Long) = "workout_detail/$workoutId"
    }

    object CreateWorkout : Screen("workout_create")
}

@Composable
fun WorkoutNavHost(navController: NavHostController = rememberNavController()) {
    NavHost(
        navController = navController,
        startDestination = Screen.WorkoutList.route
    ) {
        composable(Screen.WorkoutList.route) {
            WorkoutListScreen(navController)
        }
        composable(Screen.WorkoutDetail.route) { backStackEntry ->
            val workoutId = backStackEntry.arguments?.getString("workoutId")?.toLongOrNull()
            if (workoutId != null) {
                DetailedWorkoutScreen(workoutId, navController, viewModel = hiltViewModel())
            } else {
                Text("Invalid workout ID", color = Color.Red)
            }
        }
        composable(Screen.CreateWorkout.route) { backStackEntry ->

            DetailedWorkoutScreen(-1L, navController, viewModel = hiltViewModel())
        }
    }
}
