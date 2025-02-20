package com.example.workoutlogger.ui.detailed_workout

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.baktyiar.domain.model.Workout
import com.baktyiar.domain.repository.WorkoutRepository
import com.baktyiar.domain.model.Exercise
import com.baktyiar.domain.model.ExerciseSet
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

    fun getWorkout(id: Long) {
        viewModelScope.launch {
            _workoutsUiState.update {
                WorkoutUiState.Success(workoutRepository.getWorkoutById(1)!!)
            }
        }
    }


    fun addEmptyExercise() {
        if (_workoutsUiState.value !is WorkoutUiState.Success) return

        val currentState = _workoutsUiState.value as WorkoutUiState.Success
        val workout = currentState.workout

        val newEmptyExercise = Exercise(
            id = (workout.exercises.size + 1).toLong(),
            order = workout.exercises.size + 1
        )

        val updatedExercises = workout.exercises + newEmptyExercise
        val updatedWorkout = workout.copy(exercises = updatedExercises.toMutableList())
        _workoutsUiState.update { WorkoutUiState.Success(updatedWorkout) }
    }

    fun onExerciseChange(
        exercise: Exercise
    ) {
        if (_workoutsUiState.value !is WorkoutUiState.Success) return
        val currentState = _workoutsUiState.value as WorkoutUiState.Success
        val workout = currentState.workout

        val updatedExercises = workout.exercises.map {
            if (it.id == exercise.id) {
                exercise
            } else {
                it
            }
        }

        val newWorkout = workout.copy(exercises = updatedExercises)

        _workoutsUiState.update { WorkoutUiState.Success(newWorkout) }
    }

    fun deleteExercise(id: Long) {
        if (_workoutsUiState.value !is WorkoutUiState.Success) return

        val currentState = _workoutsUiState.value as WorkoutUiState.Success
        val workout = currentState.workout

        val updatedExercises = workout.exercises.filter { it.id != id }

        val newWorkout = workout.copy(exercises = updatedExercises)

        _workoutsUiState.update { WorkoutUiState.Success(newWorkout) }
    }

    fun addEmptySet(exerciseId: Long) {
        if (_workoutsUiState.value !is WorkoutUiState.Success) return

        val currentState = _workoutsUiState.value as WorkoutUiState.Success
        val workout = currentState.workout

        val updatedExercises = workout.exercises.map { exercise ->
            if (exercise.id == exerciseId) {

                val newEmptySet = ExerciseSet(
                    id = exercise.sets.size.plus(1).toLong(),
                    order = exercise.sets.size.plus(1),
                    weight = 0F,
                    reps = 0,
                )

                exercise.copy(sets = (exercise.sets + newEmptySet).toMutableList())
            } else {
                exercise
            }
        }

        val newWorkout = workout.copy(exercises = updatedExercises)
        _workoutsUiState.update { WorkoutUiState.Success(newWorkout) }
    }

    fun deleteSet(id: Long, exerciseId: Long) {
        if (_workoutsUiState.value !is WorkoutUiState.Success) return

        val currentState = _workoutsUiState.value as WorkoutUiState.Success
        val workout = currentState.workout

        val updatedExercises = workout.exercises.map { exercise ->
            if (exercise.id == exerciseId) {
                val updatedSet = exercise.sets.filter { it.id != id }

                exercise.copy(sets = updatedSet.toMutableList())
            } else {
                exercise
            }
        }

        val newWorkout = workout.copy(exercises = updatedExercises)

        _workoutsUiState.update { WorkoutUiState.Success(newWorkout) }
    }

    fun onSetChange(
        exerciseId: Long,
        newSet: ExerciseSet
    ) {
        if (_workoutsUiState.value !is WorkoutUiState.Success) return

        val currentState = _workoutsUiState.value as WorkoutUiState.Success
        val workout = currentState.workout
        val updatedExercises = workout.exercises.map { exercise ->
            if (exercise.id == exerciseId) {
                val updatedSet = exercise.sets.map {
                    if (it.id == newSet.id) {
                        newSet
                    } else {
                        it
                    }
                }
                Log.d("DetailedWorkoutViewModel", "onSetChange: $updatedSet")
                exercise.copy(sets = updatedSet.toMutableList())
            } else {
                exercise
            }
        }

        val newWorkout = workout.copy(exercises = updatedExercises)

        _workoutsUiState.update { WorkoutUiState.Success(newWorkout) }
    }

    fun saveWorkout() {
        if (_workoutsUiState.value !is WorkoutUiState.Success) return
        val currentState = _workoutsUiState.value as WorkoutUiState.Success
        val workout = currentState.workout
        viewModelScope.launch {
            workoutRepository.insertWorkout(workout)
        }
    }

    fun updateWorkout(workout: Workout) {
        if (_workoutsUiState.value !is WorkoutUiState.Success) return
        _workoutsUiState.update { WorkoutUiState.Success(workout) }
    }
}


sealed class WorkoutUiState {
    object Loading : WorkoutUiState()
    data class Success(var workout: Workout) : WorkoutUiState()
    data class Error(val message: String) : WorkoutUiState()
}
