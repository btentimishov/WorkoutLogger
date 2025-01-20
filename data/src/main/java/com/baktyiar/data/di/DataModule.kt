package com.baktyiar.data.di

import android.content.Context
import androidx.room.Room
import com.baktyiar.data.local.db.AppDatabase
import com.baktyiar.data.repository.WorkoutRepositoryImpl
import com.baktyiar.domain.repository.WorkoutRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun provideAppDatabase(
        @ApplicationContext context: Context
    ): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "my_workout_database"
        ).build()
    }

    @Provides
    @Singleton
    fun provideWorkoutRepository(
        db: AppDatabase
    ): WorkoutRepository {
        // Provide the repository implementation
        return WorkoutRepositoryImpl(db.workoutDao())
    }
}