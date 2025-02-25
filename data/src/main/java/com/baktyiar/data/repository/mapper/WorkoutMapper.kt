package com.baktyiar.data.repository.mapper

import com.baktyiar.data.local.entity.WorkoutEntity
import com.baktyiar.data.local.entity.WorkoutWithExercises
import com.baktyiar.domain.model.Workout


fun WorkoutWithExercises.toDomain() = Workout(
    id = workout.id,
    title = workout.title,
    dateMillis = workout.dateMillis,
    exercises = exercises.map { it.toDomain() }.toMutableList()
)

fun Workout.toEntityWithNestedLists(): WorkoutWithExercises {
    val workoutEntity = WorkoutEntity(
        id = id ?: 0L,
        title = title,
        dateMillis = dateMillis
    )
    val exerciseEntities = exercises.map { it.toEntityWithSets(workoutEntity.id) }
    return WorkoutWithExercises(workoutEntity, exerciseEntities)
}