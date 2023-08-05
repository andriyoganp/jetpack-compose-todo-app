package com.ayeee.presentation.uistate

import com.ayeee.model.Task
import com.ayeee.presentation.error.ErrorState

sealed class AllTaskUiState {
    object Loading : AllTaskUiState()

    class Error(override val message: String) : AllTaskUiState(), ErrorState

    class Success(val tasks: List<Task>) : AllTaskUiState()
}
