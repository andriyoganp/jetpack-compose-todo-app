package com.ayeee.presentation.uistate

import com.ayeee.model.Task
import com.ayeee.presentation.error.ErrorState

sealed class OverviewUiState {
    object Loading : OverviewUiState()

    class Error(override var message: String) : OverviewUiState(), ErrorState

    class Success(val tasks: List<Task>): OverviewUiState()
}
