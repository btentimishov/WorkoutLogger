package com.baktyiar.data.repository.mapper

import com.baktyiar.data.local.entity.WorkoutEntity
import com.baktyiar.data.local.entity.WorkoutWithExercises
import com.baktyiar.domain.model.Exercise
import com.baktyiar.domain.model.Workout


fun WorkoutWithExercises.toDomain() = Workout(
    id = workout.id,
    title = workout.title,
    dateMillis = workout.dateMillis,
    exercises = exercises.map { it.toDomain() }.toMutableList()
)

fun WorkoutEntity.toDomain() = Workout(
    id = id,
    title = title,
    dateMillis = dateMillis
)


fun WorkoutEntity.toDomain(exercises: List<Exercise>): Workout {
    return Workout(
        id = id,
        title = title,
        dateMillis = dateMillis,
        exercises = exercises.toMutableList()
    )
}

fun Workout.toEntity(): WorkoutEntity {
    return WorkoutEntity(
        id = id,
        title = title,
        dateMillis = dateMillis
    )
}

fun Workout.toEntityWithNestedLists(): WorkoutWithExercises {
    val workoutEntity = WorkoutEntity(
        id = id,
        title = title,
        dateMillis = dateMillis
    )
    val exerciseEntities = exercises.map { it.toEntityWithSets(id) }
    return WorkoutWithExercises(workoutEntity, exerciseEntities)
}