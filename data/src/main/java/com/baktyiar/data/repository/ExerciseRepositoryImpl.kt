package com.baktyiar.data.repository

import com.baktyiar.data.local.dao.ExerciseDao
import com.baktyiar.data.repository.mapper.toEntity
import com.baktyiar.data.repository.mapper.toDomain
import com.baktyiar.domain.model.Exercise
import com.baktyiar.domain.model.Workout
import com.baktyiar.domain.repository.ExerciseRepository
import javax.inject.Inject

class ExerciseRepositoryImpl @Inject constructor(
    private val exerciseDao: ExerciseDao
) : ExerciseRepository {

    override suspend fun getExercisesByWorkoutId(id: Long): List<Exercise> {
        return exerciseDao.getExercisesForWorkout(id).map { it.toDomain() }
    }

    override suspend fun insertExercise(exercise: Exercise, workout: Workout): Long {
        return exerciseDao.insert(exercise.toEntity(workout.id))
    }

    override suspend fun deleteWorkout(id: Long) {
        exerciseDao.deleteExerciseBy(id)
    }
}