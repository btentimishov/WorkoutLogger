package com.example.workoutlogger.ui.workout_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.baktyiar.domain.model.Workout
import com.baktyiar.domain.repository.WorkoutRepository
import com.baktyiar.ui_components.model.WorkoutUi
import com.example.workoutlogger.ui.toUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class WorkoutListUiState {
    object Loading : WorkoutListUiState()
    data class Success(var workouts: List<WorkoutUi>) : WorkoutListUiState()
    data class Error(val message: String) : WorkoutListUiState()
}

@HiltViewModel
class WorkoutListViewModel
@Inject constructor(
    private val workoutRepository: WorkoutRepository
) : ViewModel() {
    val workoutUiState: StateFlow<WorkoutListUiState> = workoutRepository.getAllWorkouts()
        .map<List<Workout>, WorkoutListUiState> { workouts ->
            WorkoutListUiState.Success(workouts.map { it.toUiModel() })
        }
        .onStart { emit(WorkoutListUiState.Loading) }
        .catch { exception -> emit(WorkoutListUiState.Error(exception.message ?: "Unknown error")) }
        .stateIn(viewModelScope, SharingStarted.Lazily, WorkoutListUiState.Loading)


    fun deleteWorkout(workoutId: Long?) {
        viewModelScope.launch {
            if (workoutId == null) return@launch
            workoutRepository.deleteWorkout(workoutId)
        }
    }
}