package com.ayeee.domain.usecase

import com.ayeee.data.repository.TaskRepository
import com.ayeee.model.ParamCreateOrUpdateTask
import javax.inject.Inject

internal class CreateTaskUseCaseImpl @Inject constructor(
    private val repository: TaskRepository
) : CreateTaskUseCase {
    override suspend fun invoke(param: ParamCreateOrUpdateTask) = repository.addTask(param)
}