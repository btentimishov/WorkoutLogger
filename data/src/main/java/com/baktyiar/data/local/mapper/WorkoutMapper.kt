package com.baktyiar.data.local.mapper

import com.baktyiar.data.local.entity.WorkoutEntity
import com.baktyiar.domain.model.Exercise
import com.baktyiar.domain.model.Workout

fun WorkoutEntity.toDomain(exercises: List<Exercise>): Workout {
    return Workout(
        id = id,
        title = title,
        dateMillis = dateMillis,
        exercises = exercises
    )
}

fun Workout.toEntity(): WorkoutEntity {
    return WorkoutEntity(
        id = id,
        title = title,
        dateMillis = dateMillis
    )
}