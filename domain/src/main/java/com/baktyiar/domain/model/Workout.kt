package com.baktyiar.domain.model

data class Workout(
    var id: Long?,
    var title: String,
    var dateMillis: Long,
    var exercises: List<Exercise> = mutableListOf()
)


data class Exercise(
    var id: Long?,
    var name: String = "",
    var order: Int,
    var sets: MutableList<ExerciseSet> = mutableListOf()
)

data class ExerciseSet(
    var id: Long?,
    var weight: Float?,
    var order: Int,
    var reps: Int,
    var isComplete: Boolean = false
)
