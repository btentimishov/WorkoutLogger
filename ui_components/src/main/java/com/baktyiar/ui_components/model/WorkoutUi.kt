package com.baktyiar.ui_components.model

data class WorkoutUi(
    val id: Long?,
    val title: String,
    val dateMillis: Long = 0L,
    val exercises: MutableList<ExerciseUi> = mutableListOf()
)

data class ExerciseUi(
    val id: Long,
    val name: String,
    val order: Int,
    val sets: MutableList<ExerciseSetUi> = mutableListOf()
)

data class ExerciseSetUi(
    val id: Long,
    val weight: Float,
    val reps: Int = 0,
    val order: Int,
    val isComplete: Boolean = false
)