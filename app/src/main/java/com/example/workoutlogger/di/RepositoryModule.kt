package com.example.workoutlogger.di

/*import com.baktyiar.data.repository.ExerciseRepositoryImpl
import com.baktyiar.data.repository.SetRepositoryImpl
import com.baktyiar.domain.repository.ExerciseRepository
import com.baktyiar.domain.repository.SetRepository*/
import com.baktyiar.data.repository.WorkoutRepositoryImpl
import com.baktyiar.domain.repository.WorkoutRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindWorkoutRepository(
        workoutRepositoryImpl: WorkoutRepositoryImpl
    ): WorkoutRepository

    /*    @Binds
        @Singleton
        abstract fun bindExerciseRepository(
            exerciseRepository: ExerciseRepositoryImpl
        ): ExerciseRepository

        @Binds
        @Singleton
        abstract fun bindSetRepository(
            setRepositoryImpl: SetRepositoryImpl
        ): SetRepository*/
}