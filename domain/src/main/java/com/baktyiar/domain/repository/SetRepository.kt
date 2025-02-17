package com.baktyiar.domain.repository

import com.baktyiar.domain.model.ExerciseSet

interface SetRepository {
    suspend fun getSetsByExerciseId(exerciseId: Long): List<ExerciseSet>
    suspend fun addSet(set: ExerciseSet, exerciseId: Long)
    suspend fun deleteSet(id: Long)
}