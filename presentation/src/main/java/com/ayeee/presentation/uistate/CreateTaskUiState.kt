package com.ayeee.presentation.uistate

import com.ayeee.presentation.error.ErrorState

sealed class CreateTaskUiState {
    object Idle : CreateTaskUiState()
    object Loading : CreateTaskUiState()

    class Error(override val message: String) : CreateTaskUiState(), ErrorState

    object Success : CreateTaskUiState()
}
