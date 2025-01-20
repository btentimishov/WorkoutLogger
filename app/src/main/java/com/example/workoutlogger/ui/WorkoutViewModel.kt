package com.example.workoutlogger.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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

@HiltViewModel
class WorkoutViewModel @Inject constructor(
    private val addWorkoutUseCase: AddWorkoutUseCase,
    getWorkoutsUseCase: GetWorkoutsUseCase
) : ViewModel() {

    // We'll collect the list of workouts as a StateFlow
    val workoutList: StateFlow<List<Workout>> = getWorkoutsUseCase()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    fun addNewWorkout(title: String) {
        viewModelScope.launch {
            val newWorkout = Workout(
                title = title,
                dateMillis = Date().time
            )
            addWorkoutUseCase(newWorkout)
        }
    }
}