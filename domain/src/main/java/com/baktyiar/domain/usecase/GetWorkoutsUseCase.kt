package com.baktyiar.domain.usecase

import com.baktyiar.domain.model.Workout
import com.baktyiar.domain.repository.WorkoutRepository
import kotlinx.coroutines.flow.Flow

class GetWorkoutsUseCase(
    private val workoutRepository: WorkoutRepository
) {

    operator fun invoke(): Flow<List<Workout>> {
        return workoutRepository.getAllWorkouts()
    }
}