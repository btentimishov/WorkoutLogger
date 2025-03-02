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
    suspend fun insertExercises(exercises: List<ExerciseEntity>) : List<Long>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSets(sets: List<SetEntity>)

    @Query("SELECT * FROM exercises WHERE workoutId = :workoutId")
    suspend fun getExercisesByWorkoutId(workoutId: Long): List<ExerciseEntity>

    @Transaction
    suspend fun insertWorkoutWithExercises(workoutWithExercises: WorkoutWithExercises): Long {
        // Insert workout and get the generated ID
        val workoutId = insert(workoutWithExercises.workout)

        workoutWithExercises.exercises.map { exerciseWithSets ->
            val exercise = exerciseWithSets.exercise.copy(workoutId = workoutId)

            val exerciseId = insertExercise(exercise)
            val sets = exerciseWithSets.sets.map { set ->
                set.copy(exerciseId = exerciseId)
            }
            insertSets(sets)
        }
        return workoutId
    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertExercise(exercise: ExerciseEntity): Long

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
