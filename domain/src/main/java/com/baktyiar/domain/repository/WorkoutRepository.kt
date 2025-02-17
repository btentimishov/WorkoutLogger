package com.baktyiar.domain.repository
import com.baktyiar.domain.model.Workout
import kotlinx.coroutines.flow.Flow


interface WorkoutRepository {

    fun getAllWorkouts(): Flow<List<Workout>>

    suspend fun getWorkoutById(id: Long): Workout?

    suspend fun insertWorkout(workout: Workout): Long

    suspend fun deleteWorkout(id: Long)
}