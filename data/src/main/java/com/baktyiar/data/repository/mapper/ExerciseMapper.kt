package com.baktyiar.data.repository.mapper

import com.baktyiar.data.local.entity.ExerciseEntity
import com.baktyiar.data.local.entity.ExerciseWithSets
import com.baktyiar.domain.model.Exercise


fun ExerciseWithSets.toDomain() = Exercise(
    id = this.exercise.id,
    name = this.exercise.name,
    order = this.exercise.order,
    sets = sets.map { it.toDomain() }.toMutableList()
)

fun Exercise.toEntityWithSets(workoutId: Long): ExerciseWithSets {
    val exerciseEntity = ExerciseEntity(
        id = id ?: 0L,
        order = order,
        workoutId = workoutId,
        name = name
    )
    val sets = sets.map {
        it.toEntity(
            exerciseId = exerciseEntity.id
        )
    }
    return ExerciseWithSets(exerciseEntity, sets)
}