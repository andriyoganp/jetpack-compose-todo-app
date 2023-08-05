package com.ayeee.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ayeee.domain.usecase.GetTaskListUseCase
import com.ayeee.model.ParamTaskList
import com.ayeee.presentation.uistate.OverviewUiState
import com.ayeee.utils.time.getTimeMillisDifferenceWithDay
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class OverviewViewModel @Inject constructor(
    getTaskListUseCase: GetTaskListUseCase,
) : ViewModel() {

    val todayUiState: StateFlow<OverviewUiState> =
        getTaskListUseCase(
            ParamTaskList(
                dueDate = getTimeMillisDifferenceWithDay() ?: 0L
            )
        ).map(OverviewUiState::Success).stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5_000L),
            OverviewUiState.Loading
        )

    val tomorrowUiState: StateFlow<OverviewUiState> =
        getTaskListUseCase(
            ParamTaskList(
                dueDate = getTimeMillisDifferenceWithDay(1) ?: 0L
            )
        ).map(OverviewUiState::Success).stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5_000L),
            OverviewUiState.Loading
        )
}