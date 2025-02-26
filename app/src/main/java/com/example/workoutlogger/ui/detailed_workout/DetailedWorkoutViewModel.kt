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
    private val _workoutsUiState: MutableStateFlow<WorkoutUiState> =
        MutableStateFlow(WorkoutUiState.Loading)

    val workoutsUiState: StateFlow<WorkoutUiState> = _workoutsUiState.asStateFlow()

    fun createEmptyWorkout() {
        viewModelScope.launch {
            val newWorkout = WorkoutUi(
                id = null,
                title = "",
                dateMillis = System.currentTimeMillis(),
                exercises = mutableListOf()
            )
            _workoutsUiState.update { WorkoutUiState.Success(newWorkout) }
        }
    }

    fun getWorkout(id: Long) {
        viewModelScope.launch {
            _workoutsUiState.update {
                val workoutResult = workoutRepository.getWorkoutById(id)
                if (workoutResult != null) {
                    WorkoutUiState.Success(workoutResult.toUiModel())
                } else {
                    WorkoutUiState.Error("Workout not found")
                }
            }
        }
    }

    fun addEmptyExercise() {
        if (_workoutsUiState.value !is WorkoutUiState.Success) return

        val currentState = _workoutsUiState.value as WorkoutUiState.Success
        val workout = currentState.workout

        val newEmptyExercise = ExerciseUi(
            id = 0L,
            name = "",
            order = workout.exercises.size + 1
        )

        val updatedExercises = workout.exercises + newEmptyExercise
        val updatedWorkout = workout.copy(exercises = updatedExercises.toMutableList())
        _workoutsUiState.update { WorkoutUiState.Success(updatedWorkout) }
    }

    fun onExerciseChange(
        exercise: ExerciseUi
    ) {
        if (_workoutsUiState.value !is WorkoutUiState.Success) return
        val currentState = _workoutsUiState.value as WorkoutUiState.Success
        val workout = currentState.workout

        val updatedExercises = workout.exercises.map {
            if (it.order == exercise.order) {
                exercise
            } else {
                it
            }
        }

        val newWorkout = workout.copy(exercises = updatedExercises.toMutableList())

        _workoutsUiState.update { WorkoutUiState.Success(newWorkout) }
    }

    fun deleteExercise(exercise: ExerciseUi) {
        if (_workoutsUiState.value !is WorkoutUiState.Success) return

        val currentState = _workoutsUiState.value as WorkoutUiState.Success
        val workout = currentState.workout

        val updatedExercises = workout.exercises.filter { it.order != exercise.order }

        val newWorkout = workout.copy(exercises = updatedExercises.toMutableList())

        _workoutsUiState.update { WorkoutUiState.Success(newWorkout) }
    }

    fun addEmptySet(exerciseToChange: ExerciseUi) {
        if (_workoutsUiState.value !is WorkoutUiState.Success) return

        val currentState = _workoutsUiState.value as WorkoutUiState.Success
        val workout = currentState.workout

        val updatedExercises = workout.exercises.map { exercise ->
            if (exercise.order == exerciseToChange.order) {

                val newEmptySet = ExerciseSetUi(
                    id = 0L,
                    order = exercise.sets.size.plus(1),
                    weight = 0F,
                    reps = 0,
                )

                exercise.copy(sets = (exercise.sets + newEmptySet).toMutableList())
            } else {
                exercise
            }
        }

        val newWorkout = workout.copy(exercises = updatedExercises.toMutableList())
        _workoutsUiState.update { WorkoutUiState.Success(newWorkout) }
    }

    fun deleteSet(exerciseToChange: ExerciseUi, setToDelete: ExerciseSetUi) {
        if (_workoutsUiState.value !is WorkoutUiState.Success) return

        val currentState = _workoutsUiState.value as WorkoutUiState.Success
        val workout = currentState.workout

        val updatedExercises = workout.exercises.map { exercise ->
            if (exercise.order == exerciseToChange.order) {
                val updatedSet = exercise.sets.filter { set ->
                    set.order != setToDelete.order
                }

                exercise.copy(sets = updatedSet.toMutableList())
            } else {
                exercise
            }
        }

        val newWorkout = workout.copy(exercises = updatedExercises.toMutableList())

        _workoutsUiState.update { WorkoutUiState.Success(newWorkout) }
    }

    fun onSetChange(
        exerciseToChange: ExerciseUi,
        newSet: ExerciseSetUi
    ) {
        if (_workoutsUiState.value !is WorkoutUiState.Success) return

        val currentState = _workoutsUiState.value as WorkoutUiState.Success
        val workout = currentState.workout
        val updatedExercises = workout.exercises.map { exercise ->
            if (exercise.order == exerciseToChange.order) {
                val updatedSet = exercise.sets.map { set ->
                    if (set.order == newSet.order) {
                        newSet
                    } else {
                        set
                    }
                }
                exercise.copy(sets = updatedSet.toMutableList())
            } else {
                exercise
            }
        }

        val newWorkout = workout.copy(exercises = updatedExercises.toMutableList())

        _workoutsUiState.update { WorkoutUiState.Success(newWorkout) }
    }

    fun saveWorkout() {
        if (_workoutsUiState.value !is WorkoutUiState.Success) return
        val currentState = _workoutsUiState.value as WorkoutUiState.Success
        val workout = currentState.workout
        viewModelScope.launch {
            workoutRepository.insertWorkout(workout.toDomain())
        }
    }

    fun updateWorkout(workout: WorkoutUi) {
        _workoutsUiState.update { WorkoutUiState.Success(workout) }
    }

    fun deleteWorkout(workoutId: Long) {
        viewModelScope.launch {
            workoutRepository.deleteWorkout(workoutId)
        }
    }
}


sealed class WorkoutUiState {
    object Loading : WorkoutUiState()
    data class Success(var workout: WorkoutUi) : WorkoutUiState()
    data class Error(val message: String) : WorkoutUiState()
}
