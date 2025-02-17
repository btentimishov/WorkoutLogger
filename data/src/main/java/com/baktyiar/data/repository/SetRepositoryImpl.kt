package com.baktyiar.data.repository

import com.baktyiar.data.local.dao.SetDao
import com.baktyiar.data.repository.mapper.toDomain
import com.baktyiar.data.repository.mapper.toEntity
import com.baktyiar.domain.model.ExerciseSet
import com.baktyiar.domain.repository.SetRepository
import javax.inject.Inject

class SetRepositoryImpl @Inject constructor(
    private val exerciseSetDao: SetDao) : SetRepository {

    override suspend fun getSetsByExerciseId(exerciseId: Long): List<ExerciseSet> {
        return exerciseSetDao.getSetsForExercise(exerciseId).map { it.toDomain() }
    }

    override suspend fun addSet(set: ExerciseSet, exerciseId: Long) {
        exerciseSetDao.insert(set.toEntity(exerciseId))
    }

    override suspend fun deleteSet(id: Long) {
        exerciseSetDao.deleteSetBy(id)
    }
}