package com.baktyiar.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.baktyiar.data.local.dao.ExerciseDao
import com.baktyiar.data.local.dao.SetDao
import com.baktyiar.data.local.dao.WorkoutDao
import com.baktyiar.data.local.entity.ExerciseEntity
import com.baktyiar.data.local.entity.SetEntity
import com.baktyiar.data.local.entity.WorkoutEntity

@Database(entities = [WorkoutEntity::class, ExerciseEntity::class, SetEntity::class], version = 1, exportSchema = false)
abstract class WorkoutDatabase : RoomDatabase() {
    abstract fun workoutDao(): WorkoutDao
    abstract fun exerciseDao(): ExerciseDao
    abstract fun setDao(): SetDao
}
