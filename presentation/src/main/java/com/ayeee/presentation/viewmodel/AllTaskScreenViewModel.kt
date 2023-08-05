package com.ayeee.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ayeee.domain.usecase.DeleteTaskUseCase
import com.ayeee.domain.usecase.GetTaskListUseCase
import com.ayeee.model.ParamTaskList
import com.ayeee.presentation.uistate.AllTaskUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AllTaskScreenViewModel @Inject constructor(
    getTaskListUseCase: GetTaskListUseCase,
    private val deleteTaskUseCase: DeleteTaskUseCase,
) : ViewModel() {
    val allTaskUiState: StateFlow<AllTaskUiState> =
        getTaskListUseCase(ParamTaskList())
            .map(AllTaskUiState::Success)
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000L),
                initialValue = AllTaskUiState.Loading,
            )

    fun deleteAll() {
        viewModelScope.launch {
            deleteTaskUseCase.deleteAll()
        }
    }
}