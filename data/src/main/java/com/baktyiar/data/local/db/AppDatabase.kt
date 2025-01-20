package com.baktyiar.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.baktyiar.data.local.dao.WorkoutDao
import com.baktyiar.data.local.entity.ExerciseEntity
import com.baktyiar.data.local.entity.WorkoutEntity


@Database(
    entities = [
        WorkoutEntity::class,
        ExerciseEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun workoutDao(): WorkoutDao
}