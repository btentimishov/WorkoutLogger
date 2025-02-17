package com.example.workoutlogger

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.lifecycleScope
import com.baktyiar.data.local.dao.ExerciseDao
import com.baktyiar.data.local.dao.SetDao
import com.baktyiar.data.local.dao.WorkoutDao
import com.baktyiar.data.local.entity.ExerciseEntity
import com.baktyiar.data.local.entity.SetEntity
import com.baktyiar.data.local.entity.WorkoutEntity
import com.baktyiar.ui_components.theme.WorkoutLoggerTheme
import com.example.workoutlogger.ui.DetailedWorkoutScreen
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
                DetailedWorkoutScreen()
            }
        }
    }

    private suspend fun testDatabaseOperations() {
        workoutDao.insert(
            WorkoutEntity(
                id = 1,
                title = "Leg day workout",
                dateMillis = System.currentTimeMillis()
            )
        )
    }
}
