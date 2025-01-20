package com.baktyiar.domain.model

data class Workout(
    val id: Long = 0L,
    val title: String,
    val dateMillis: Long,                // e.g., System.currentTimeMillis()
    val exercises: List<Exercise> = emptyList()
)