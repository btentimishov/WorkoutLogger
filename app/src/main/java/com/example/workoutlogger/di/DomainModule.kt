package com.example.workoutlogger.di

import com.baktyiar.domain.repository.WorkoutRepository
import com.baktyiar.domain.usecase.AddWorkoutUseCase
import com.baktyiar.domain.usecase.GetWorkoutsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DomainModule {

    @Provides
    @Singleton
    fun provideAddWorkoutUseCase(
        workoutRepository: WorkoutRepository
    ): AddWorkoutUseCase {
        return AddWorkoutUseCase(workoutRepository)
    }

    @Provides
    @Singleton
    fun provideGetWorkoutsUseCase(
        workoutRepository: WorkoutRepository
    ): GetWorkoutsUseCase {
        return GetWorkoutsUseCase(workoutRepository)
    }
}
