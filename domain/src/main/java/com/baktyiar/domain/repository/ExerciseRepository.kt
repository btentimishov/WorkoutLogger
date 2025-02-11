package com.baktyiar.domain.repository

import com.baktyiar.domain.model.Exercise
import kotlinx.coroutines.flow.Flow

interface ExerciseRepository {

    fun getAllExercises(): Flow<List<Exercise>>

    fun getExercisesByWorkoutId(id: Long): Flow<List<Exercise>>

    suspend fun getExerciseById(id: Long): Exercise?

    suspend fun insertExercise(exercise: Exercise): Long

    suspend fun updateExercise(exercise: Exercise)

    suspend fun deleteWorkout(id: Long)
}