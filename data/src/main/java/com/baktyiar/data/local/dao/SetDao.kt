package com.baktyiar.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.baktyiar.data.local.entity.SetEntity

@Dao
interface SetDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(set: SetEntity): Long

    @Query("SELECT * FROM sets WHERE exerciseId = :exerciseId")
    suspend fun getSetsForExercise(exerciseId: Long): List<SetEntity>

    @Delete
    suspend fun deleteSet(set: SetEntity)
}
