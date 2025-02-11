package com.baktyiar.data.di

import android.content.Context
import androidx.room.Room
import com.baktyiar.data.local.dao.ExerciseDao
import com.baktyiar.data.local.dao.SetDao
import com.baktyiar.data.local.dao.WorkoutDao
import com.baktyiar.data.local.db.WorkoutDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
@Module
@InstallIn(SingletonComponent::class)
object WorkoutDatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): WorkoutDatabase {
        return Room.databaseBuilder(
            context,
            WorkoutDatabase::class.java,
            "workout_db"
        ).fallbackToDestructiveMigration().build()
    }

    @Provides
    fun provideWorkoutDao(database: WorkoutDatabase): WorkoutDao {
        return database.workoutDao()
    }

    @Provides
    fun provideExerciseDao(database: WorkoutDatabase): ExerciseDao {
        return database.exerciseDao()
    }

    @Provides
    fun provideSetDao(database: WorkoutDatabase): SetDao {
        return database.setDao()
    }
}
