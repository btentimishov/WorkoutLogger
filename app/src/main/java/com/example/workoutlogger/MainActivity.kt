package com.example.workoutlogger

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.lifecycleScope
import com.baktyiar.data.local.dao.ExerciseDao
import com.baktyiar.data.local.dao.SetDao
import com.baktyiar.data.local.dao.WorkoutDao
import com.baktyiar.data.local.entity.ExerciseEntity
import com.baktyiar.data.local.entity.SetEntity
import com.baktyiar.data.local.entity.WorkoutEntity
import com.baktyiar.data.local.entity.WorkoutWithExercises
import com.baktyiar.ui_components.theme.WorkoutLoggerTheme
import com.example.workoutlogger.ui.detailed_workout.DetailedWorkoutScreen
import com.example.workoutlogger.ui.workout_list.WorkoutListScreen
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
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
//        lifecycleScope.launch {
//            createMockData()
//        }
        setContent {
            WorkoutLoggerTheme {
                WorkoutNavHost()
            }
        }
    }

    suspend fun createMockData() {
        val workoutTitles = listOf("Push Day", "Pull Day", "Leg Day", "Upper Body", "Full Body")
        val workouts = workoutTitles.mapIndexed { index, title ->
            WorkoutEntity(
                id = (index + 1).toLong(),  // IDs 1..5
                title = title,
                // Just using currentTimeMillis minus some day-offset to mimic distinct dates
                dateMillis = System.currentTimeMillis() - (index * 24 * 60 * 60 * 1000L)
            )
        }

        // 2) Prepare some exercise names to cycle through
        val sampleExercises = listOf("Squat", "Bench Press", "Deadlift", "Shoulder Press", "Biceps Curl")

        workouts.forEach {
            workoutDao.insert(it)
        }
        // 3) For each workout, assign 5 exercises, each with 3 sets
        workouts.forEach { workout ->
            sampleExercises.forEachIndexed { index, exerciseName ->
                val exerciseEntity = ExerciseEntity(
                    workoutId = workout.id,
                    name = exerciseName,
                    order = index + 1
                )
                val exerId = exerciseDao.insert(exerciseEntity)

                for (setOrder in 1..3) {
                    val setEntity = SetEntity(
                        exerciseId = exerId,
                        weight = (40.44).toFloat(),  // 40–90 kg (approx.)
                        reps = (8..12).random(),
                        order = setOrder,
                        isComplete = false
                    )
                    setDao.insert(setEntity)
                }
            }
        }
    }
}
