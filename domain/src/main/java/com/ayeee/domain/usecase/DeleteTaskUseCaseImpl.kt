package com.ayeee.domain.usecase

import com.ayeee.data.repository.TaskRepository
import javax.inject.Inject

internal class DeleteTaskUseCaseImpl @Inject constructor(
    private val taskRepository: TaskRepository
) : DeleteTaskUseCase {
    override suspend fun delete(id: Long): Int = taskRepository.deleteTask(id)

    override suspend fun deleteAll(): Int = taskRepository.deleteAllTask()
}