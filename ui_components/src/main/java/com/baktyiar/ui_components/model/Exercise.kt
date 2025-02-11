package com.baktyiar.ui_components.model

data class Exercise(
    val id: String,
    val name: String,
    val sets: List<ExerciseSet>
)