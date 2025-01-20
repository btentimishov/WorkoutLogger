package com.baktyiar.data.repository

import com.baktyiar.data.local.dao.WorkoutDao
import com.baktyiar.data.local.mapper.toDomain
import com.baktyiar.data.local.mapper.toEntity
import com.baktyiar.domain.model.Workout
import com.baktyiar.domain.repository.WorkoutRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class WorkoutRepositoryImpl(
    private val workoutDao: WorkoutDao
) : WorkoutRepository {

    override fun getAllWorkouts(): Flow<List<Workout>> {
        // We transform the Flow<List<WorkoutEntity>> -> Flow<List<Workout>>
        return workoutDao.getAllWorkouts().map { workoutEntities ->
            workoutEntities.map { workoutEntity ->
                // For each WorkoutEntity, also retrieve its exercises
                val exerciseEntities = workoutDao.getExercisesForWorkout(workoutEntity.id)
                val exercises = exerciseEntities.map { it.toDomain() }
                workoutEntity.toDomain(exercises)
            }
        }
    }

    override suspend fun getWorkoutById(id: Long): Workout? {
        val entity = workoutDao.getWorkoutById(id) ?: return null
        val exerciseEntities = workoutDao.getExercisesForWorkout(id)
        val exercises = exerciseEntities.map { it.toDomain() }
        return entity.toDomain(exercises)
    }

    override suspend fun insertWorkout(workout: Workout): Long {
        // Insert workout
        val workoutId = workoutDao.insertWorkout(workout.toEntity())
        // Insert each exercise
        workout.exercises.forEach { exercise ->
            workoutDao.insertExercise(exercise.toEntity(workoutId))
        }
        return workoutId
    }

    override suspend fun updateWorkout(workout: Workout) {
        // Update the workout entity
        workoutDao.updateWorkout(workout.toEntity())

        // For simplicity, let's delete & re-insert exercises
        // A more robust approach might update existing rows, add new, etc.
        val existingExercises = workoutDao.getExercisesForWorkout(workout.id)
        existingExercises.forEach {
            workoutDao.deleteExerciseById(it.id)
        }
        workout.exercises.forEach { exercise ->
            workoutDao.insertExercise(exercise.toEntity(workout.id))
        }
    }

    override suspend fun deleteWorkout(id: Long) {
        workoutDao.deleteWorkoutById(id)  // CASCADE should also remove exercises
    }
}
