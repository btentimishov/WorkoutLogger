package com.example.workoutlogger.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.baktyiar.data.local.dao.WorkoutDao
import com.baktyiar.domain.model.Workout
import com.baktyiar.domain.usecase.AddWorkoutUseCase
import com.baktyiar.domain.usecase.GetWorkoutsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.util.Date
import javax.inject.Inject
/*

@HiltViewModel
class WorkoutViewModel @Inject constructor(
    private val workoutDao: WorkoutDao
) : ViewModel() {
    init {
        viewModelScope.launch {

        }
    }
}*/
