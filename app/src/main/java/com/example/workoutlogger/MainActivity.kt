package com.example.workoutlogger

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.lifecycleScope
import com.baktyiar.data.local.dao.WorkoutDao
import com.baktyiar.data.local.entity.WorkoutEntity
import com.baktyiar.ui_components.theme.WorkoutLoggerTheme
import com.example.workoutlogger.ui.detailed_workout.DetailedWorkoutScreen
import com.example.workoutlogger.ui.workout_list.WorkoutListScreen
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var workoutDao: WorkoutDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycleScope.launch {
            testDatabaseOperations()
        }
        setContent {
            WorkoutLoggerTheme {
                WorkoutNavHost()
            }
        }
    }

    private suspend fun testDatabaseOperations() {
        workoutDao.insert(
            WorkoutEntity(
                id = 1,
                title = "Leg day",
                dateMillis = System.currentTimeMillis()
            )
        )
        workoutDao.insert(
            WorkoutEntity(
                id = 2,
                title = "Chest day",
                dateMillis = System.currentTimeMillis()
            )
        )
        workoutDao.insert(
            WorkoutEntity(
                id = 3,
                title = "Back day",
                dateMillis = System.currentTimeMillis()
            )
        )
    }
}
