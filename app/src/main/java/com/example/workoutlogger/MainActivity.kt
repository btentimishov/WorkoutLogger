package com.example.workoutlogger

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Text
import androidx.lifecycle.lifecycleScope
import com.baktyiar.data.local.dao.ExerciseDao
import com.baktyiar.data.local.dao.SetDao
import com.baktyiar.data.local.dao.WorkoutDao
import com.baktyiar.data.local.entity.ExerciseEntity
import com.baktyiar.data.local.entity.SetEntity
import com.baktyiar.data.local.entity.WorkoutEntity
import com.baktyiar.ui_components.theme.WorkoutLoggerTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var workoutDao: WorkoutDao
    @Inject
    lateinit var exerciseDao: ExerciseDao
    @Inject
    lateinit var setDao: SetDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycleScope.launch {
            testDatabaseOperations()
        }
        setContent {
            WorkoutLoggerTheme {
                Text("Hello world")
            }
        }
    }

    private suspend fun testDatabaseOperations() {
        Log.d("WorkoutTest", "Starting database test...")

        // Insert Workout
        val workoutId = workoutDao.insert(
            WorkoutEntity(
                title = "Test Workout",
                dateMillis = System.currentTimeMillis()
            )
        )
        Log.d("WorkoutTest", "Inserted Workout with ID: $workoutId")

        // Insert Exercise
        val exerciseId = exerciseDao.insert(
            ExerciseEntity(
                workoutId = workoutId,
                name = "Bench Press",
                order = 1
            )
        )
        Log.d("WorkoutTest", "Inserted Exercise with ID: $exerciseId")

        // Insert Set
        val setId =
            setDao.insert(SetEntity(exerciseId = exerciseId, weight = 80f, reps = 10, order = 1))
        Log.d("WorkoutTest", "Inserted Set with ID: $setId")

        // Retrieve Data
        val allWorkouts = workoutDao.getAllWorkouts()
        Log.d("WorkoutTest", "All Workouts: $allWorkouts")

        val workoutWithExercises = workoutDao.getWorkoutWithExercises(workoutId)
        Log.d("WorkoutTest", "Workout with Exercises: $workoutWithExercises")

        val exercises = exerciseDao.getExercisesForWorkout(workoutId)
        Log.d("WorkoutTest", "Exercises in Workout: $exercises")

        val sets = setDao.getSetsForExercise(exerciseId)
        Log.d("WorkoutTest", "Sets in Exercise: $sets")

        // Delete Test Data
        workoutDao.deleteWorkout(allWorkouts.first())
        Log.d("WorkoutTest", "Deleted Workout. Remaining: ${workoutDao.getAllWorkouts()}")
    }
}
