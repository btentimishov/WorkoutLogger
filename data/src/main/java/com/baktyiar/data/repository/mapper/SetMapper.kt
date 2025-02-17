package com.baktyiar.data.repository.mapper

import com.baktyiar.data.local.entity.SetEntity
import com.baktyiar.domain.model.ExerciseSet

fun SetEntity.toDomain() = ExerciseSet(
    id = id,
    weight = weight,
    reps = reps,
    order = order
)

fun ExerciseSet.toEntity(exerciseId: Long) = SetEntity(
    exerciseId = exerciseId,
    weight = weight,
    reps = reps,
    order = order,
    isComplete = isComplete
)