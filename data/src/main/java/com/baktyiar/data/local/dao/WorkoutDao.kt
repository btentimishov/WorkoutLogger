package com.baktyiar.data.local.dao

import androidx.room.*
import com.baktyiar.data.local.entity.ExerciseEntity
import com.baktyiar.data.local.entity.WorkoutEntity
import com.baktyiar.data.local.entity.WorkoutWithExercises
import kotlinx.coroutines.flow.Flow

@Dao
interface WorkoutDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(workout: WorkoutEntity): Long

    @Query("SELECT * FROM workouts")
    suspend fun getAllWorkouts(): List<WorkoutEntity>

    @Transaction
    @Query("SELECT * FROM workouts WHERE id = :workoutId")
    suspend fun getWorkoutWithExercises(workoutId: Long): WorkoutWithExercises

    @Delete
    suspend fun deleteWorkout(workout: WorkoutEntity)
}
