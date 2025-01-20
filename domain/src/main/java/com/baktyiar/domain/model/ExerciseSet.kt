package com.baktyiar.domain.model

data class ExerciseSet(
    val id: Long = 0L,
    val weight: Float?,  // null if no weight is used
    val reps: Int
)
