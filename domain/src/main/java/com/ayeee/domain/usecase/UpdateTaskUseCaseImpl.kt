package com.ayeee.domain.usecase

import com.ayeee.data.repository.TaskRepository
import com.ayeee.model.ParamCreateOrUpdateTask
import javax.inject.Inject

internal class UpdateTaskUseCaseImpl @Inject constructor(
    private val taskRepository: TaskRepository
) : UpdateTaskUseCase {
    override suspend fun invoke(param: ParamCreateOrUpdateTask) = taskRepository.updateTask(param)
}