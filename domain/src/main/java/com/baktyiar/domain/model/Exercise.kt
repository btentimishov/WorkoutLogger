package com.baktyiar.domain.model

data class Exercise(
    val id: Long = 0L,
    val name: String,
    val sets: List<ExerciseSet> = emptyList()
)
