package com.baktyiar.data.local.entity

import androidx.room.Embedded
import androidx.room.Relation

data class ExerciseWithSets(
    @Embedded val exercise: ExerciseEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "exerciseId",
        entity = SetEntity::class
    )
    val sets: List<SetEntity> = emptyList()
)
