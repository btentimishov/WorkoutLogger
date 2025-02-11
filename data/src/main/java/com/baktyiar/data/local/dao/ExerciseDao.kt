package com.baktyiar.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.baktyiar.data.local.entity.ExerciseEntity

@Dao
interface ExerciseDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(exercise: ExerciseEntity): Long

    @Query("SELECT * FROM exercises WHERE workoutId = :workoutId ORDER BY `order` ASC")
    suspend fun getExercisesForWorkout(workoutId: Long): List<ExerciseEntity>

    @Delete
    suspend fun deleteExercise(exercise: ExerciseEntity)
}
