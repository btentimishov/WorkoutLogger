package com.example.workoutlogger.ui.workout_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.baktyiar.domain.model.Workout
import com.baktyiar.domain.repository.WorkoutRepository
import com.baktyiar.ui_components.model.WorkoutUi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

sealed class WorkoutListUiState {
    object Loading : WorkoutListUiState()
    data class Success(var workouts: List<Workout>) : WorkoutListUiState()
    data class Error(val message: String) : WorkoutListUiState()
}

@HiltViewModel
class WorkoutListViewModel
@Inject constructor(
    workoutRepository: WorkoutRepository
) : ViewModel() {
    val workoutUiState: StateFlow<WorkoutListUiState> = workoutRepository.getAllWorkouts()
        .map<List<Workout>, WorkoutListUiState> { workouts ->
            WorkoutListUiState.Success(workouts)
        }
        .onStart { emit(WorkoutListUiState.Loading) }
        .catch { exception -> emit(WorkoutListUiState.Error(exception.message ?: "Unknown error")) }
        .stateIn(viewModelScope, SharingStarted.Lazily, WorkoutListUiState.Loading)
}