package com.baktyiar.data.local.mapper

import com.baktyiar.data.local.entity.ExerciseEntity
import com.baktyiar.domain.model.Exercise

fun ExerciseEntity.toDomain(): Exercise {
    return Exercise(
        id = id,
        name = name
        // For simplicity, ignoring sets. Add your own logic for sets if needed.
    )
}

fun Exercise.toEntity(workoutId: Long): ExerciseEntity {
    return ExerciseEntity(
        id = id,
        workoutId = workoutId,
        name = name
    )
}