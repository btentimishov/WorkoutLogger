package com.baktyiar.domain.model

data class Workout(
    var id: Long = 0L,
    var title: String,
    var dateMillis: Long,
    var exercises: List<Exercise> = mutableListOf()
)


data class Exercise(
    var id: Long = 0L,
    var name: String,
    var order: Int,
    var sets: MutableList<ExerciseSet> = mutableListOf()
)

data class ExerciseSet(
    var id: Long = 0L,
    var weight: Float,
    var order: Int,
    var reps: Int,
    var isComplete: Boolean = false
)
