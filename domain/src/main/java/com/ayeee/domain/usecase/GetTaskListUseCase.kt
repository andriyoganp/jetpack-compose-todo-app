package com.ayeee.domain.usecase

import com.ayeee.model.ParamTaskList
import com.ayeee.model.Task
import kotlinx.coroutines.flow.Flow

interface GetTaskListUseCase {
    operator fun invoke(param: ParamTaskList): Flow<List<Task>>
}