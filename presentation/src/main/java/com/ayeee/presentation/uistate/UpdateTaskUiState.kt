package com.ayeee.presentation.uistate

import com.ayeee.presentation.error.ErrorState

sealed class UpdateTaskUiState {
    object Idle : UpdateTaskUiState()

    object Loading : UpdateTaskUiState()

    class Error(override val message: String) : UpdateTaskUiState(), ErrorState

    object Success : UpdateTaskUiState()
}