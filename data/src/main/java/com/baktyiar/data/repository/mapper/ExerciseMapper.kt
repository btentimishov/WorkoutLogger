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


fun ExerciseEntity.toDomain() = Exercise(
    id = id,
    name = name,
    order = order
)

fun Exercise.toEntity(workoutId: Long): ExerciseEntity {
    return ExerciseEntity(
        id = id,
        order = order,
        workoutId = workoutId,
        name = name
    )
}

fun Exercise.toEntityWithSets(workoutId: Long): ExerciseWithSets {
    val exerciseEntity = ExerciseEntity(
        id = id,
        order = order,
        workoutId = workoutId,
        name = name
    )
    val sets = sets.map {
        it.toEntity(
            exerciseId = id
        )
    }
    return ExerciseWithSets(exerciseEntity, sets)
}