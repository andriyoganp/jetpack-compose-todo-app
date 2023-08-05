package com.ayeee.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ayeee.domain.usecase.DeleteTaskUseCase
import com.ayeee.domain.usecase.UpdateTaskUseCase
import com.ayeee.model.ParamCreateOrUpdateTask
import com.ayeee.presentation.uistate.UpdateTaskUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TaskDetailViewModel @Inject constructor(
    private val updateTaskUseCase: UpdateTaskUseCase,
    private val deleteTaskUseCase: DeleteTaskUseCase,
) : ViewModel() {

    private val _updateTaskUiState = MutableStateFlow<UpdateTaskUiState>(UpdateTaskUiState.Idle)
    val updateTaskState = _updateTaskUiState.asStateFlow().stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        UpdateTaskUiState.Idle
    )

    fun update(title: String, description: String, type: String? = null, dueDate: Long? = null) {
        viewModelScope.launch {
            _updateTaskUiState.value = UpdateTaskUiState.Loading
            try {
                updateTaskUseCase.invoke(
                    ParamCreateOrUpdateTask(
                        title = title,
                        description = description,
                        type = type,
                        dueDate = dueDate
                    )
                )
                _updateTaskUiState.value = UpdateTaskUiState.Success
            } catch (e: Exception) {
                _updateTaskUiState.value = UpdateTaskUiState.Error("Failure Update Task")
            }
        }
    }

    fun delete(id: Long) {
        viewModelScope.launch {
            _updateTaskUiState.value = UpdateTaskUiState.Loading
            deleteTaskUseCase.delete(id)
        }
    }
}