package com.example.workoutlogger.ui.detailed_workout

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.baktyiar.domain.repository.WorkoutRepository
import com.baktyiar.ui_components.model.ExerciseSetUi
import com.baktyiar.ui_components.model.ExerciseUi
import com.baktyiar.ui_components.model.WorkoutUi
import com.example.workoutlogger.ui.toDomain
import com.example.workoutlogger.ui.toUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailedWorkoutViewModel @Inject constructor(
    private val workoutRepository: WorkoutRepository
) : ViewModel() {
    private val _uiState: MutableStateFlow<WorkoutUiState> =
        MutableStateFlow(WorkoutUiState.Loading)

    val uiState: StateFlow<WorkoutUiState> = _uiState.asStateFlow()

    fun createEmptyWorkout() {
        viewModelScope.launch {
            val newWorkout = WorkoutUi(
                id = null,
                title = "",
                dateMillis = System.currentTimeMillis(),
                exercises = mutableListOf()
            )
            _uiState.update { WorkoutUiState.Success(newWorkout) }
        }
    }

    fun getWorkout(id: Long) {
        viewModelScope.launch {
            val workoutResult = workoutRepository.getWorkoutById(id)
            _uiState.update {
                workoutResult?.let {
                    WorkoutUiState.Success(it.toUiModel())
                } ?: WorkoutUiState.Error("Workout not found")
            }
        }
    }

    fun addEmptyExercise() {
        viewModelScope.launch {
            updateStateIfSuccess { workout ->
                val newExercise = ExerciseUi(
                    id = 0L,
                    name = "",
                    order = workout.exercises.size + 1
                )
                workout.copy(exercises = (workout.exercises + newExercise).toMutableList())
            }
        }
    }

    fun onExerciseChange(exercise: ExerciseUi) {
        updateStateIfSuccess { workout ->
            val updatedExercises = workout.exercises.map {
                if (it.order == exercise.order) exercise else it
            }
            workout.copy(exercises = updatedExercises.toMutableList())
        }
    }

    fun deleteExercise(exercise: ExerciseUi) {
        updateStateIfSuccess { workout ->
            val updatedExercises = workout.exercises
                .filter { it.order != exercise.order }
                .mapIndexed { index, exercise ->
                    exercise.copy(order = index + 1)
                }
            workout.copy(exercises = updatedExercises.toMutableList())
        }
    }

    fun addEmptySet(exerciseToChange: ExerciseUi) {
        updateStateIfSuccess { workout ->
            val updatedExercises = workout.exercises.map { exercise ->
                if (exercise.order == exerciseToChange.order) {
                    val newEmptySet = ExerciseSetUi(
                        id = 0L,
                        order = exercise.sets.size + 1,
                        weight = 0F,
                        reps = 0
                    )
                    exercise.copy(sets = (exercise.sets + newEmptySet).toMutableList())
                } else {
                    exercise
                }
            }
            workout.copy(exercises = updatedExercises.toMutableList())
        }
    }

    fun deleteSet(exerciseToChange: ExerciseUi, setToDelete: ExerciseSetUi) {
        updateStateIfSuccess { workout ->
            val updatedExercises = workout.exercises
                .map { exercise ->
                    if (exercise.order == exerciseToChange.order) {
                        val updatedSets = exercise.sets
                            .filter { it.order != setToDelete.order }
                            .mapIndexed { index, set ->
                                set.copy(order = index + 1)
                            }
                        exercise.copy(sets = updatedSets.toMutableList())
                    } else {
                        exercise
                    }
                }
            workout.copy(exercises = updatedExercises.toMutableList())
        }
    }

    fun onSetChange(exerciseToChange: ExerciseUi, newSet: ExerciseSetUi) {
        updateStateIfSuccess { workout ->
            val updatedExercises = workout.exercises.map { exercise ->
                if (exercise.order == exerciseToChange.order) {
                    val updatedSets = exercise.sets.map { set ->
                        if (set.order == newSet.order) newSet else set
                    }
                    exercise.copy(sets = updatedSets.toMutableList())
                } else {
                    exercise
                }
            }
            workout.copy(exercises = updatedExercises.toMutableList())
        }
    }

    fun saveWorkout() {
        val currentWorkout = (_uiState.value as? WorkoutUiState.Success)?.workout ?: return
        viewModelScope.launch {
            workoutRepository.insertWorkout(currentWorkout.toDomain())
        }
    }

    fun updateWorkout(workout: WorkoutUi) {
        _uiState.update { WorkoutUiState.Success(workout) }
    }

    fun deleteWorkout(workoutId: Long) {
        viewModelScope.launch {
            workoutRepository.deleteWorkout(workoutId)
        }
    }

    private inline fun updateStateIfSuccess(crossinline transform: (WorkoutUi) -> WorkoutUi) {
        val currentState = _uiState.value as? WorkoutUiState.Success ?: return
        _uiState.update { WorkoutUiState.Success(transform(currentState.workout)) }
    }
}

sealed class WorkoutUiState {
    object Loading : WorkoutUiState()
    data class Success(val workout: WorkoutUi) : WorkoutUiState()
    data class Error(val message: String) : WorkoutUiState()
}