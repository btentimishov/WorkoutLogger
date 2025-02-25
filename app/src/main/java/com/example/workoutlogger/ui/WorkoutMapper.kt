package com.example.workoutlogger.ui

import com.baktyiar.domain.model.Exercise
import com.baktyiar.domain.model.ExerciseSet
import com.baktyiar.domain.model.Workout
import com.baktyiar.ui_components.model.ExerciseUi as ExerciseUi
import com.baktyiar.ui_components.model.ExerciseSetUi as ExerciseSetUi
import com.baktyiar.ui_components.model.WorkoutUi as WorkoutUi

fun Workout.toUiModel(): WorkoutUi =
    WorkoutUi(
        id = id,
        title = title,
        dateMillis = dateMillis,
        exercises = exercises.map { it.toUiModel() }.toMutableList()
    )


fun Exercise.toUiModel(): ExerciseUi =
    ExerciseUi(
        id = id,
        name = name,
        order = order,
        sets = sets.map { it.toUiModel() }.toMutableList()
    )

fun ExerciseSet.toUiModel(): ExerciseSetUi =
    ExerciseSetUi(
        id = id,
        weight = weight,
        reps = reps,
        order = order,
        isComplete = isComplete
    )


fun WorkoutUi.toDomain(): Workout =
    Workout(
        id = id,
        title = title,
        dateMillis = dateMillis,
        exercises = exercises.map { it.toDomain() }.toMutableList())

fun ExerciseSetUi.toDomain(): ExerciseSet =
    ExerciseSet(
        id = id,
        weight = weight,
        order = order,
        reps = reps,
        isComplete = isComplete
    )

fun ExerciseUi.toDomain(): Exercise =
    Exercise(
        id = id,
        name = name,
        sets = sets.map { it.toDomain() }.toMutableList(),
        order = order
    )
