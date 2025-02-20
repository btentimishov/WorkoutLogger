package com.baktyiar.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.baktyiar.data.local.entity.ExerciseEntity
import com.baktyiar.data.local.entity.SetEntity
import com.baktyiar.data.local.entity.WorkoutEntity
import com.baktyiar.data.local.entity.WorkoutWithExercises
import kotlinx.coroutines.flow.Flow

@Dao
interface WorkoutDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(workout: WorkoutEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertExercises(exercises: List<ExerciseEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSets(sets: List<SetEntity>)

    @Transaction
    suspend fun insertWorkoutWithExercises(workoutWithExercises: WorkoutWithExercises): Long {
        // Insert workout and get the generated ID
        val workoutId = insert(workoutWithExercises.workout)

        // Insert exercises with the generated workoutId
        val exercises = workoutWithExercises.exercises.map { exerciseWithSets ->
            exerciseWithSets.exercise.copy(workoutId = workoutId) // Assign foreign key
        }

        insertExercises(exercises)


        // Insert sets with correct exerciseId
        val sets = workoutWithExercises.exercises.flatMap { exerciseWithSets ->
            exerciseWithSets.sets.map { set ->
                set.copy(exerciseId = exerciseWithSets.exercise.id) // Assign FK
            }
        }
        insertSets(sets)
        return workoutId
    }

    @Transaction
    @Query("SELECT * FROM workouts WHERE id = :workoutId")
    suspend fun getWorkoutWithExercises(workoutId: Long): WorkoutWithExercises

    @Transaction
    @Query("SELECT * FROM workouts")
    fun getAllWorkoutsWithExercises(): Flow<List<WorkoutWithExercises>>

    @Query("SELECT * FROM workouts")
    fun getAllWorkouts(): Flow<List<WorkoutEntity>>

    @Delete
    suspend fun deleteWorkout(workout: WorkoutEntity)

    @Query("DELETE FROM workouts WHERE id = :id")
    suspend fun deleteWorkoutBy(id: Long)
}
