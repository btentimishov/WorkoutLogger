package com.baktyiar.data.repository

import com.baktyiar.data.local.dao.WorkoutDao
import com.baktyiar.data.repository.mapper.toEntity
import com.baktyiar.data.repository.mapper.toDomain
import com.baktyiar.domain.model.Workout
import com.baktyiar.domain.repository.WorkoutRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class WorkoutRepositoryImpl @Inject constructor(
    private val workoutDao: WorkoutDao
) : WorkoutRepository {
    override fun getAllWorkouts(): Flow<List<Workout>> {
        TODO("Not yet implemented")
    }

    override suspend fun getWorkoutById(id: Long): Workout? {
        return workoutDao.getWorkoutWithExercises(id).toDomain()
    }

    override suspend fun insertWorkout(workout: Workout): Long {
        return workoutDao.insert(workout = workout.toEntity())
    }

    override suspend fun deleteWorkout(id: Long) {
        workoutDao.deleteWorkoutBy(id)
    }
}