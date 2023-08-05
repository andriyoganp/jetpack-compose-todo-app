package com.ayeee.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ayeee.domain.usecase.CreateTaskUseCase
import com.ayeee.model.ParamCreateOrUpdateTask
import com.ayeee.presentation.uistate.CreateTaskUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreateTaskViewModel @Inject constructor(
    private val createTaskUseCase: CreateTaskUseCase
) : ViewModel() {

    private val _createTaskState = MutableStateFlow<CreateTaskUiState>(CreateTaskUiState.Idle)
    val createTaskState = _createTaskState.asStateFlow().stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        CreateTaskUiState.Idle
    )

    fun create(title: String, description: String, type: String? = null, dueDate: Long? = null) {
        viewModelScope.launch {
            _createTaskState.value = CreateTaskUiState.Loading
            val result = createTaskUseCase.invoke(
                ParamCreateOrUpdateTask(
                    title = title,
                    description = description,
                    type = type,
                    dueDate = dueDate
                )
            )
            if (result > 0) {
                _createTaskState.value = CreateTaskUiState.Success
            }  else {
                _createTaskState.value = CreateTaskUiState.Error("Failure to insert")
            }
        }
    }
}