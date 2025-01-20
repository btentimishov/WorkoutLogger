package com.baktyiar.domain.usecase

import com.baktyiar.domain.model.Workout
import com.baktyiar.domain.repository.WorkoutRepository

class AddWorkoutUseCase(
    private val workoutRepository: WorkoutRepository
) {

    suspend operator fun invoke(workout: Workout): Long {
        return workoutRepository.insertWorkout(workout)
    }
}